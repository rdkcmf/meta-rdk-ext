#ifndef GST_EXTERNAL_SINK_H
#define GST_EXTERNAL_SINK_H

#include <gst/gst.h>
#include <gst/base/gstbasesink.h>

G_BEGIN_DECLS

#define GST_EXTERNAL_SINK_TYPE          (gst_external_sink_get_type())
#define GST_EXTERNAL_SINK(obj)          (G_TYPE_CHECK_INSTANCE_CAST((obj), GST_EXTERNAL_SINK_TYPE, GstExternalSink))
#define GST_EXTERNAL_SINK_CLASS(klass)  (G_TYPE_CHECK_CLASS_CAST((klass), GST_EXTERNAL_SINK_TYPE, GstExternalSinkClass))

typedef struct _GstExternalSink           GstExternalSink;
typedef struct _GstExternalSinkClass      GstExternalSinkClass;
typedef struct _GstExternalSinkPrivate    GstExternalSinkPrivate;

GType gst_external_sink_get_type(void);

struct _GstExternalSink {
  GstBaseSink parent;

  /*< private >*/
  GstExternalSinkPrivate *priv;

  /*< private >*/
  gpointer     _gst_reserved[GST_PADDING];
};

struct _GstExternalSinkClass {
  GstBaseSinkClass parent_class;
};

G_END_DECLS

#endif /* GST_EXTERNAL_SINK_H */
