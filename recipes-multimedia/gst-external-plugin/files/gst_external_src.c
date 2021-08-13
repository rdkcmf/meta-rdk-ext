#include "gst_external_src.h"

struct _GstExternalSrcPrivate {
  gchar* uri;
};

static GstStaticPadTemplate src_template =
  GST_STATIC_PAD_TEMPLATE("src",
                          GST_PAD_SRC,
                          GST_PAD_ALWAYS,
                          GST_STATIC_CAPS("video/external"));

static void gst_external_src_uri_handler_init(gpointer gIface, gpointer ifaceData);

GST_DEBUG_CATEGORY_STATIC(gst_external_src_debug_category);
#define GST_CAT_DEFAULT gst_external_src_debug_category

#define gst_external_src_parent_class parent_class
G_DEFINE_TYPE_WITH_CODE(
  GstExternalSrc,
  gst_external_src,
  GST_TYPE_PUSH_SRC,
  G_ADD_PRIVATE(GstExternalSrc)
  G_IMPLEMENT_INTERFACE(GST_TYPE_URI_HANDLER, gst_external_src_uri_handler_init)
  GST_DEBUG_CATEGORY_INIT (gst_external_src_debug_category, "externalsrc", 0, "externalsrc element"));

enum {
  PROP_0,
  PROP_LOCATION,
  PROP_IS_LIVE,
};

static void gst_external_src_finalize(GObject* object)
{
  GstExternalSrc* src = GST_EXTERNAL_SRC(object);
  GstExternalSrcPrivate* priv = src->priv;

  g_free(priv->uri);

  GST_CALL_PARENT(G_OBJECT_CLASS, finalize, (object));
}

static void gst_external_src_set_property(GObject* object,
                                    guint propID,
                                    const GValue* value,
                                    GParamSpec* pspec)
{
  GstExternalSrc* src = GST_EXTERNAL_SRC(object);

  switch (propID) {
    case PROP_LOCATION:
      gst_uri_handler_set_uri((GstURIHandler*)(src),
                              g_value_get_string(value), 0);
      break;
    default:
      G_OBJECT_WARN_INVALID_PROPERTY_ID(object, propID, pspec);
      break;
  }
}

static void gst_external_src_get_property(GObject* object,
                                          guint propID,
                                          GValue* value,
                                          GParamSpec* pspec)
{
  GstExternalSrc* src = GST_EXTERNAL_SRC(object);
  GstExternalSrcPrivate* priv = src->priv;

  GST_OBJECT_LOCK(src);
  switch (propID) {
    case PROP_LOCATION:
      g_value_set_string(value, priv->uri);
      break;
    case PROP_IS_LIVE:
      g_value_set_boolean(value, TRUE);
      break;
    default:
      G_OBJECT_WARN_INVALID_PROPERTY_ID(object, propID, pspec);
      break;
  }
  GST_OBJECT_UNLOCK(src);
}

static GstFlowReturn
gst_external_src_create (GstPushSrc * src, GstBuffer ** outbuf)
{
  GstExternalSrc *externalsrc = GST_EXTERNAL_SRC (src);
  GstExternalSrcPrivate *priv = externalsrc->priv;

  GstBuffer *buf;
  buf = gst_buffer_new_allocate (NULL, 1, NULL);
  if (G_UNLIKELY (buf == NULL))
    goto alloc_failed;

  GST_BUFFER_PTS (buf) = GST_CLOCK_TIME_NONE;
  GST_BUFFER_DTS (buf) = GST_CLOCK_TIME_NONE;

  *outbuf = buf;

  g_usleep(16000);

  return GST_FLOW_OK;

alloc_failed:
  GST_ERROR_OBJECT (src, "Failed to allocate");
  return GST_FLOW_ERROR;
}

static gboolean
gst_external_src_is_seekable (GstBaseSrc * src)
{
  return FALSE;
}

static gboolean
gst_external_src_query (GstBaseSrc * basesrc, GstQuery * query)
{
  gboolean ret = FALSE;
  GstExternalSrc *src = GST_EXTERNAL_SRC (basesrc);

  switch (GST_QUERY_TYPE (query)) {
    case GST_QUERY_URI:
      gst_query_set_uri (query, src->priv->uri);
      ret = TRUE;
      break;
    default:
      ret = FALSE;
      break;
  }

  if (!ret)
    ret = GST_BASE_SRC_CLASS (parent_class)->query (basesrc, query);

  return ret;
}

static void gst_external_src_class_init(GstExternalSrcClass* klass)
{
  GObjectClass *gobject_class;
  GstElementClass *gstelement_class;
  GstBaseSrcClass *gstbasesrc_class;
  GstPushSrcClass *gstpushsrc_class;

  gobject_class = G_OBJECT_CLASS(klass);
  gstelement_class = GST_ELEMENT_CLASS(klass);
  gstbasesrc_class = GST_BASE_SRC_CLASS (klass);
  gstpushsrc_class = GST_PUSH_SRC_CLASS (klass);

  gobject_class->finalize = gst_external_src_finalize;
  gobject_class->set_property = gst_external_src_set_property;
  gobject_class->get_property = gst_external_src_get_property;

  g_object_class_install_property(
    gobject_class, PROP_IS_LIVE,
    g_param_spec_boolean(
      "is-live", "Is Live",
      "Let playbin know we are a live source.",
      TRUE, (GParamFlags)(G_PARAM_READABLE | G_PARAM_STATIC_STRINGS)));

  gst_element_class_set_static_metadata (
    gstelement_class,
    "External source",
    GST_ELEMENT_FACTORY_KLASS_SRC,
    "A dummy source to trigger auto-pluging of externalsink",
    "Comcast");
  gst_element_class_add_pad_template(gstelement_class, gst_static_pad_template_get(&src_template));

  gstbasesrc_class->is_seekable = GST_DEBUG_FUNCPTR (gst_external_src_is_seekable);
  gstbasesrc_class->query = GST_DEBUG_FUNCPTR (gst_external_src_query);

  gstpushsrc_class->create = GST_DEBUG_FUNCPTR (gst_external_src_create);
}

static void gst_external_src_init(GstExternalSrc* src)
{
  GstBaseSrc* basesrc = GST_BASE_SRC (src);
  GstExternalSrcPrivate* priv = (GstExternalSrcPrivate*)gst_external_src_get_instance_private(src);
  src->priv = priv;

  gst_base_src_set_live (basesrc, TRUE);
  gst_base_src_set_format (basesrc, GST_FORMAT_TIME);
  gst_base_src_set_do_timestamp (basesrc, TRUE);
}

/* uri handler interface */
static GstURIType gst_external_src_uri_get_type(GType type)
{
  return GST_URI_SRC;
}

const gchar* const* gst_external_src_get_protocols(GType type)
{
  static const char* protocols[] = {
    "external",
#ifdef GST_EXT_SRC_PROTOCOL
    G_STRINGIFY(GST_EXT_SRC_PROTOCOL),
#endif
    0
  };
  return protocols;
}

static gchar* gst_external_src_get_uri(GstURIHandler* handler)
{
  GstExternalSrc* src = GST_EXTERNAL_SRC(handler);
  GstExternalSrcPrivate* priv = src->priv;
  gchar* ret;
  GST_OBJECT_LOCK(src);
  ret = g_strdup(priv->uri);
  GST_OBJECT_UNLOCK(src);
  return ret;
}

static gboolean gst_external_src_set_uri(
  GstURIHandler* handler,
  const gchar* uri,
  GError** error)
{
  GstExternalSrc* src = GST_EXTERNAL_SRC(handler);
  GstExternalSrcPrivate* priv = src->priv;
  GST_OBJECT_LOCK(src);
  g_free(priv->uri);
  if (uri) {
    priv->uri = g_strdup(uri);
  } else {
    priv->uri = 0;
  }
  GST_OBJECT_UNLOCK(src);
  return TRUE;
}

static void gst_external_src_uri_handler_init(gpointer gIface, gpointer data)
{
  GstURIHandlerInterface* iface = (GstURIHandlerInterface*)gIface;

  iface->get_type = gst_external_src_uri_get_type;
  iface->get_protocols = gst_external_src_get_protocols;
  iface->get_uri = gst_external_src_get_uri;
  iface->set_uri = gst_external_src_set_uri;
}
