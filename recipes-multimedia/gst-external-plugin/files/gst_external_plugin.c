#include "gst_external_src.h"
#include "gst_external_sink.h"

#define PACKAGE_ORIGIN "http://example.com"
#define PACKAGE "externalsrc"

static gboolean gst_external_plugin_init (GstPlugin * plugin)
{
    if ( !gst_element_register (
             plugin,
             "externalsrc",
             GST_RANK_MARGINAL,
             gst_external_src_get_type()) )
        return FALSE;

    if ( !gst_element_register (
             plugin,
             "externalsink",
             GST_RANK_MARGINAL,
             gst_external_sink_get_type()) )
        return FALSE;

    return TRUE;
}

GST_PLUGIN_DEFINE (
    GST_VERSION_MAJOR,
    GST_VERSION_MINOR,
    external,
    "reference external plugin",
    gst_external_plugin_init,
    "0.1",
    "LGPL",
    PACKAGE,
    PACKAGE_ORIGIN )
