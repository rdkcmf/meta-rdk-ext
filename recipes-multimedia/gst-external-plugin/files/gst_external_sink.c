#include "gst_external_sink.h"

#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>

#include <wayland-client.h>
#include <simpleshell-client-protocol.h>
#include <simplebuffer-client-protocol.h>
#include <vpc-client-protocol.h>

#define DEFAULT_WINDOW_X (0)
#define DEFAULT_WINDOW_Y (0)
#define DEFAULT_WINDOW_WIDTH (1280)
#define DEFAULT_WINDOW_HEIGHT (720)

#define LOCK( sink ) g_mutex_lock( &((sink)->priv->mutex) );
#define UNLOCK( sink ) g_mutex_unlock( &((sink)->priv->mutex) );

static GstStaticPadTemplate sinktemplate =
  GST_STATIC_PAD_TEMPLATE (
    "sink",
    GST_PAD_SINK,
    GST_PAD_ALWAYS,
    GST_STATIC_CAPS(
      "video/external; "
      "video/x-ext-dvb; "
    )
  );

struct _GstExternalSinkPrivate {
  struct wl_display *display;
  struct wl_registry *registry;
  struct wl_simple_shell *shell;
  struct wl_compositor *compositor;
  struct wl_event_queue *queue;
  struct wl_surface *surface;
  uint32_t surfaceId;
  struct wl_vpc *vpc;
  struct wl_vpc_surface *vpcSurface;
  struct wl_output *output;
  struct wl_sb *sb;

  GMutex mutex;

  int transX;
  int transY;
  int scaleXNum;
  int scaleXDenom;
  int scaleYNum;
  int scaleYDenom;

  int windowX;
  int windowY;
  int windowWidth;
  int windowHeight;

  gboolean windowChange;
  gboolean windowSizeOverride;

  gboolean visible;

  gboolean quitDispatchThread;
  GThread *dispatchThread;
};

GST_DEBUG_CATEGORY_STATIC(gst_external_sink_debug_category);
#define GST_CAT_DEFAULT gst_external_sink_debug_category

#define gst_external_sink_parent_class parent_class
G_DEFINE_TYPE_WITH_CODE (
  GstExternalSink,
  gst_external_sink,
  GST_TYPE_BASE_SINK,
  G_ADD_PRIVATE(GstExternalSink)
  GST_DEBUG_CATEGORY_INIT (gst_external_sink_debug_category, "externalsink", 0, "externalsink element"));

enum
{
  PROP_0,
  PROP_WINDOW_SET,
};

static void gst_external_sink_update_video_position(GstExternalSink* sink);

static void shellSurfaceId(void *data,
                           struct wl_simple_shell *wl_simple_shell,
                           struct wl_surface *surface,
                           uint32_t surfaceId)
{
   GstExternalSink *sink = (GstExternalSink*)data;
   GstExternalSinkPrivate* priv = sink->priv;
   wl_fixed_t z;
   char name[32];

   priv->surfaceId = surfaceId;
   g_snprintf( name, 32, "external-sink-surface-%x", surfaceId );
   wl_simple_shell_set_name( priv->shell, surfaceId, name );
   wl_simple_shell_set_visible( priv->shell, priv->surfaceId, TRUE);
   if ( !priv->vpc )
     wl_simple_shell_set_geometry( priv->shell, priv->surfaceId, priv->windowX, priv->windowY, priv->windowWidth, priv->windowHeight );
   z = wl_fixed_from_double(0.0);
   wl_simple_shell_set_zorder( priv->shell, priv->surfaceId, z);

   wl_display_flush(priv->display);
}

static void shellSurfaceCreated(void *data,
                                struct wl_simple_shell *wl_simple_shell,
                                uint32_t surfaceId,
                                const char *name)
{ }

static void shellSurfaceDestroyed(void *data,
                                  struct wl_simple_shell *wl_simple_shell,
                                  uint32_t surfaceId,
                                  const char *name)
{ }

static void shellSurfaceStatus(void *data,
                               struct wl_simple_shell *wl_simple_shell,
                               uint32_t surfaceId,
                               const char *name,
                               uint32_t visible,
                               int32_t x,
                               int32_t y,
                               int32_t width,
                               int32_t height,
                               wl_fixed_t opacity,
                               wl_fixed_t zorder)
{
}

static void shellGetSurfacesDone(void *data, struct wl_simple_shell *wl_simple_shell )
{
}

static const struct wl_simple_shell_listener shellListener =
{
   shellSurfaceId,
   shellSurfaceCreated,
   shellSurfaceDestroyed,
   shellSurfaceStatus,
   shellGetSurfacesDone
};

static void vpcVideoPathChange(void *data,
                               struct wl_vpc_surface *wl_vpc_surface,
                               uint32_t new_pathway )
{
  g_print("external-sink: new pathway: %d\n", new_pathway);
}

static void vpcVideoXformChange(void *data,
                                struct wl_vpc_surface *wl_vpc_surface,
                                int32_t x_translation,
                                int32_t y_translation,
                                uint32_t x_scale_num,
                                uint32_t x_scale_denom,
                                uint32_t y_scale_num,
                                uint32_t y_scale_denom,
                                uint32_t output_width,
                                uint32_t output_height)
{
   GstExternalSink *sink = (GstExternalSink*)data;
   GstExternalSinkPrivate* priv = sink->priv;

   LOCK( sink );
   priv->transX = x_translation;
   priv->transY = y_translation;
   if ( x_scale_denom )
   {
      priv->scaleXNum = x_scale_num;
      priv->scaleXDenom = x_scale_denom;
   }
   if ( y_scale_denom )
   {
      priv->scaleYNum = y_scale_num;
      priv->scaleYDenom = y_scale_denom;
   }
   priv->windowChange = TRUE;
   UNLOCK( sink );

   gst_external_sink_update_video_position( sink );
}

static const struct wl_vpc_surface_listener vpcListener = {
   vpcVideoPathChange,
   vpcVideoXformChange
};

static void outputHandleGeometry( void *data,
                                  struct wl_output *output,
                                  int x,
                                  int y,
                                  int mmWidth,
                                  int mmHeight,
                                  int subPixel,
                                  const char *make,
                                  const char *model,
                                  int transform )
{ }

static void outputHandleMode( void *data,
                              struct wl_output *output,
                              uint32_t flags,
                              int width,
                              int height,
                              int refreshRate )
{
   GstExternalSink *sink = (GstExternalSink*)data;
   GstExternalSinkPrivate* priv = sink->priv;

   if ( flags & WL_OUTPUT_MODE_CURRENT )
   {
      LOCK( sink );
      if ( !priv->windowSizeOverride )
      {
         g_print("external-priv: compositor sets window to (%dx%d)\n", width, height);
         priv->windowWidth = width;
         priv->windowHeight = height;
         if ( priv->vpcSurface )
         {
            wl_vpc_surface_set_geometry( priv->vpcSurface, priv->windowX, priv->windowY, priv->windowWidth, priv->windowHeight );
         }
      }
      UNLOCK( sink );
   }
}

static void outputHandleDone( void *data,
                              struct wl_output *output )
{
}
static void outputHandleScale( void *data,
                               struct wl_output *output,
                               int32_t scale )
{
}
static const struct wl_output_listener outputListener = {
   outputHandleGeometry,
   outputHandleMode,
   outputHandleDone,
   outputHandleScale
};

static void registryHandleGlobal(void *data,
                                 struct wl_registry *registry, uint32_t id,
                               const char *interface, uint32_t version)
{
   GstExternalSink *sink = (GstExternalSink*)data;
   GstExternalSinkPrivate* priv = sink->priv;
   int len;

   g_print("external-sink: registry: id %d interface (%s) version %d\n", id, interface, version );

   len = strlen(interface);
   if ((len==13) && (strncmp(interface, "wl_compositor",len) == 0))
   {
     priv->compositor = (struct wl_compositor*)wl_registry_bind(registry, id, &wl_compositor_interface, 1);
      g_print("external-sink: compositor %p\n", (void*)priv->compositor);
      wl_proxy_set_queue((struct wl_proxy*)priv->compositor, priv->queue);
   }
   else if ((len==15) && (strncmp(interface, "wl_simple_shell",len) == 0))
   {
      priv->shell = (struct wl_simple_shell*)wl_registry_bind(registry, id, &wl_simple_shell_interface, 1);
      g_print("external-sink: shell %p\n", (void*)priv->shell);
      wl_proxy_set_queue((struct wl_proxy*)priv->shell, priv->queue);
      wl_simple_shell_add_listener(priv->shell, &shellListener, sink);
   }
   else if ((len==6) && (strncmp(interface, "wl_vpc", len) ==0))
   {
      priv->vpc = (struct wl_vpc*)wl_registry_bind(registry, id, &wl_vpc_interface, 1);
      g_print("external-sink: registry: vpc %p\n", (void*)priv->vpc);
      wl_proxy_set_queue((struct wl_proxy*)priv->vpc, priv->queue);
   }
   else if ((len==9) && !strncmp(interface, "wl_output", len) )
   {
      priv->output = (struct wl_output*)wl_registry_bind(registry, id, &wl_output_interface, 2);
      g_print("external-sink: registry: output %p\n", (void*)priv->output);
      wl_proxy_set_queue((struct wl_proxy*)priv->output, priv->queue);
      wl_output_add_listener(priv->output, &outputListener, sink);
   }
   else if ((len==5) && (strncmp(interface, "wl_sb", len) == 0))
   {
      priv->sb = (struct wl_sb*)wl_registry_bind(registry, id, &wl_sb_interface, 1);
      g_print("external-sink: registry: sb %p\n", (void*)priv->sb);
      wl_proxy_set_queue((struct wl_proxy*)priv->sb, priv->queue);
   }

   wl_display_flush(priv->display);
}

static void registryHandleGlobalRemove(void *data,
                                       struct wl_registry *registry,
                                    uint32_t name)
{
   GstExternalSink *sink = (GstExternalSink*)data;
}

static const struct wl_registry_listener registryListener =
{
  registryHandleGlobal,
  registryHandleGlobalRemove
};

static gpointer wlDispatchThread(gpointer data)
{
   GstExternalSink *sink = (GstExternalSink*)data;
   GstExternalSinkPrivate* priv = sink->priv;
   if ( priv->display )
   {
      GST_DEBUG("dispatchThread: enter");
      while( !priv->quitDispatchThread )
      {
         if ( wl_display_dispatch_queue( priv->display, priv->queue ) == -1 )
         {
            break;
         }
      }
      GST_DEBUG("dispatchThread: exit");
   }
   return NULL;
}

static void
gst_external_sink_set_property (GObject * object, guint prop_id,
                                const GValue * value, GParamSpec * pspec)
{
  GstExternalSink *sink = GST_EXTERNAL_SINK (object);
  GstExternalSinkPrivate* priv = sink->priv;
  switch (prop_id) {
    case PROP_WINDOW_SET: {
      const gchar *str = g_value_get_string(value);
      gchar **parts = g_strsplit(str, ",", 4);

      if ( !parts[0] || !parts[1] || !parts[2] || !parts[3] )
      {
        GST_ERROR( "Bad window properties string '%s'", str );
      }
      else
      {
        int nx, ny, nw, nh;
        nx = atoi( parts[0] );
        ny = atoi( parts[1] );
        nw = atoi( parts[2] );
        nh = atoi( parts[3] );

        if ( (nx != priv->windowX) ||
             (ny != priv->windowY) ||
             (nw != priv->windowWidth) ||
             (nh != priv->windowHeight) )
        {
          LOCK( sink );
          priv->windowX = nx;
          priv->windowY = ny;
          priv->windowWidth = nw;
          priv->windowHeight = nh;
          priv->windowSizeOverride = TRUE;
          priv->windowChange = TRUE;
          UNLOCK( sink );

          g_print("gst_external_sink_set_property set window rect (%d,%d,%d,%d)\n",
                 priv->windowX, priv->windowY, priv->windowWidth, priv->windowHeight );

          if ( priv->vpcSurface )
          {
            wl_vpc_surface_set_geometry( priv->vpcSurface, priv->windowX, priv->windowY, priv->windowWidth, priv->windowHeight );
          }
          if ( priv->shell && priv->surfaceId )
          {
            wl_simple_shell_set_geometry( priv->shell, priv->surfaceId, priv->windowX, priv->windowY, priv->windowWidth, priv->windowHeight );
            wl_simple_shell_get_status( priv->shell, priv->surfaceId);
            wl_display_flush( priv->display );
          }

          gst_external_sink_update_video_position( sink );
        }
      }
      g_strfreev(parts);
      break;
    }
    default:
      G_OBJECT_WARN_INVALID_PROPERTY_ID (object, prop_id, pspec);
      break;
  }
}

static void
gst_external_sink_get_property (GObject * object, guint prop_id, GValue * value,
    GParamSpec * pspec)
{
  GstExternalSink *sink;
  sink = GST_EXTERNAL_SINK (object);

  switch (prop_id) {
    case PROP_WINDOW_SET:
      break;
    default:
      G_OBJECT_WARN_INVALID_PROPERTY_ID (object, prop_id, pspec);
      break;
  }
}

static gboolean gst_external_sink_start(GstBaseSink *basesink)
{
  GstExternalSink *sink = GST_EXTERNAL_SINK (basesink);
  GstExternalSinkPrivate* priv = sink->priv;
  if ( priv->display )
    return TRUE;

  priv->display = wl_display_connect(NULL);
  if ( priv->display ) {
    priv->queue = wl_display_create_queue(priv->display);
    if ( priv->queue )  {
      priv->registry = wl_display_get_registry( priv->display );
      if ( priv->registry ) {
        wl_proxy_set_queue((struct wl_proxy*)priv->registry, priv->queue);
        wl_registry_add_listener(priv->registry, &registryListener, sink);
        wl_display_roundtrip_queue(priv->display, priv->queue);

        priv->surface = wl_compositor_create_surface(priv->compositor);
        g_print("external-sink: start: surface=%p\n", (void*)priv->surface);
        wl_proxy_set_queue((struct wl_proxy*)priv->surface, priv->queue);
        wl_display_flush( priv->display );
      } else {
        GST_ERROR("external-sink: unable to get display registry");
      }
    } else {
      GST_ERROR("external-sink: unable to create queue");
    }
  } else {
    GST_ERROR("external-sink: unable to create display");
  }

  if ( priv->vpc && priv->surface ) {
    priv->vpcSurface = wl_vpc_get_vpc_surface( priv->vpc, priv->surface );
    if ( priv->vpcSurface ) {
      wl_proxy_set_queue((struct wl_proxy*)priv->vpcSurface, priv->queue);
      wl_vpc_surface_add_listener( priv->vpcSurface, &vpcListener, sink );
      if ( (priv->windowWidth != DEFAULT_WINDOW_WIDTH) || (priv->windowHeight != DEFAULT_WINDOW_HEIGHT) ) {
        wl_vpc_surface_set_geometry( priv->vpcSurface, priv->windowX, priv->windowY, priv->windowWidth, priv->windowHeight );
      }
      wl_display_flush( priv->display );
      g_print("external-sink: done add vpcSurface listener\n");
    } else {
      GST_ERROR("external-sink: failed to create vpcSurface");
    }
  } else {
    GST_ERROR("external-sink: can't create vpc surface: vpc %p surface %p", priv->vpc, priv->surface);
  }

  if ( priv->display ) {
    priv->quitDispatchThread = FALSE;
    if ( priv->dispatchThread == NULL ) {
      GST_DEBUG_OBJECT(sink, "starting dispatch thread");
      priv->dispatchThread = g_thread_new("westeros_priv_dispatch", wlDispatchThread, sink);
    }
  }

  return TRUE;
}

static gboolean gst_external_sink_stop(GstBaseSink *basesink)
{
  GstExternalSink *sink = GST_EXTERNAL_SINK (basesink);
  GstExternalSinkPrivate* priv = sink->priv;

  if ( priv->dispatchThread ) {
    priv->quitDispatchThread = TRUE;
    if ( priv->display ) {
      int fd = wl_display_get_fd( priv->display );
      if ( fd >= 0 )
        shutdown( fd, SHUT_RDWR );
    }
    g_thread_join( priv->dispatchThread );
    priv->dispatchThread= NULL;
  }

  if ( priv->display ) {
    if ( priv->vpcSurface ) {
      wl_vpc_surface_destroy( priv->vpcSurface );
      priv->vpcSurface = 0;
    }
    if ( priv->output ) {
      wl_output_destroy( priv->output );
      priv->output = 0;
    }
    if ( priv->vpc ) {
      wl_vpc_destroy( priv->vpc );
      priv->vpc = 0;
    }
    if ( priv->surface ) {
      wl_surface_destroy( priv->surface );
      priv->surface = 0;
    }
    if ( priv->display && priv->queue ) {
      wl_display_flush( priv->display );
      wl_display_roundtrip_queue( priv->display, priv->queue );
    }
    if ( priv->compositor ) {
      wl_compositor_destroy( priv->compositor );
      priv->compositor = 0;
    }
    if ( priv->shell ) {
      wl_simple_shell_destroy( priv->shell );
      priv->shell = 0;
    }
    if ( priv->registry ) {
      wl_registry_destroy( priv->registry );
      priv->registry = 0;
    }
    if ( priv->queue ) {
      wl_event_queue_destroy( priv->queue );
      priv->queue = 0;
    }
    if ( priv->sb ) {
      wl_sb_destroy( priv->sb );
      priv->sb = 0;
    }
    if ( priv->display ) {
      wl_display_disconnect(priv->display);
      priv->display = 0;
    }
  }

  return TRUE;
}

static void
gst_external_sink_update_video_position(GstExternalSink* sink)
{
  int windowX, windowY;
  int windowWidth, windowHeight;
  int videoX, videoY;
  int videoWidth, videoHeight;

  GstExternalSinkPrivate* priv = sink->priv;

  LOCK( sink );
  if ( !priv->windowChange ) {
    UNLOCK( sink );
    return;
  }
  priv->windowChange = FALSE;
  windowX = priv->windowX;
  windowY = priv->windowY;
  windowWidth = priv->windowWidth;
  windowHeight = priv->windowHeight;
  videoX = ((priv->windowX*priv->scaleXNum)/priv->scaleXDenom) + priv->transX;
  videoY = ((priv->windowY*priv->scaleYNum)/priv->scaleYDenom) + priv->transY;
  videoWidth = (priv->windowWidth*priv->scaleXNum)/priv->scaleXDenom;
  videoHeight = (priv->windowHeight*priv->scaleYNum)/priv->scaleYDenom;
  UNLOCK( sink );

  GST_DEBUG_OBJECT(
    sink,
    "window=(%d,%d,%d,%d), video=(%d,%d,%d,%d)\n",
    windowX, windowY, windowWidth, windowHeight,
    videoX, videoY, videoWidth, videoHeight);

  // Send a buffer to compositor to update hole punch geometry
  if ( priv->sb )
  {
    struct wl_buffer *buff;
    buff= wl_sb_create_buffer( priv->sb,
                               0,
                               windowWidth,
                               windowHeight,
                               windowWidth*4,
                               WL_SB_FORMAT_ARGB8888 );
    wl_surface_attach( priv->surface, buff, windowX, windowY );
    wl_surface_damage( priv->surface, 0, 0, windowWidth, windowHeight );
    wl_surface_commit( priv->surface );
  }
}

static GstFlowReturn
gst_external_sink_render (GstBaseSink * bsink, GstBuffer * buffer)
{
  GstExternalSink *sink = GST_EXTERNAL_SINK (bsink);
  GstExternalSinkPrivate* priv = sink->priv;

  return GST_FLOW_OK;
}

static void
gst_external_sink_finalize(GObject *object)
{
   GstExternalSink *sink = GST_EXTERNAL_SINK(object);
   GstExternalSinkPrivate* priv = sink->priv;
   g_mutex_clear( &priv->mutex );
   GST_CALL_PARENT (G_OBJECT_CLASS, finalize, (object));
}

static void
gst_external_sink_class_init (GstExternalSinkClass * klass)
{
  GObjectClass *gobject_class;
  GstElementClass *gstelement_class;
  GstBaseSinkClass *gstbasesink_class;

  gobject_class = G_OBJECT_CLASS (klass);
  gstelement_class = GST_ELEMENT_CLASS (klass);
  gstbasesink_class = GST_BASE_SINK_CLASS (klass);

  gobject_class->finalize= gst_external_sink_finalize;
  gobject_class->set_property= gst_external_sink_set_property;
  gobject_class->get_property= gst_external_sink_get_property;

  g_object_class_install_property (
    gobject_class,
    PROP_WINDOW_SET,
    g_param_spec_string (
      "rectangle", "rectangle",
      "Window Set Format: x,y,width,height",
      NULL, G_PARAM_WRITABLE));

  gst_element_class_set_static_metadata (
    gstelement_class,
    "External sink",
    "Codec/Decoder/Video/Sink",
    "External sink", "Comcast");
  gst_element_class_add_static_pad_template (gstelement_class, &sinktemplate);

  gstbasesink_class->start = GST_DEBUG_FUNCPTR (gst_external_sink_start);
  gstbasesink_class->stop = GST_DEBUG_FUNCPTR (gst_external_sink_stop);
  gstbasesink_class->render = GST_DEBUG_FUNCPTR (gst_external_sink_render);
}

static void
gst_external_sink_init (GstExternalSink *sink)
{
  GstExternalSinkPrivate* priv = (GstExternalSinkPrivate*)gst_external_sink_get_instance_private(sink);
  sink->priv = priv;
  g_mutex_init( &priv->mutex );

  priv->windowX = DEFAULT_WINDOW_X;
  priv->windowY = DEFAULT_WINDOW_Y;
  priv->windowWidth = DEFAULT_WINDOW_WIDTH;
  priv->windowHeight = DEFAULT_WINDOW_HEIGHT;
  priv->windowChange = FALSE;
  priv->windowSizeOverride = FALSE;

  priv->transX = 0;
  priv->transY = 0;
  priv->scaleXNum = 1;
  priv->scaleXDenom = 1;
  priv->scaleYNum = 1;
  priv->scaleYDenom = 1;
}
