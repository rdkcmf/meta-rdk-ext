FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0001-backported-push_sample-from-gst-1.5.91.patch \
                   file://0001-backported-playbin-fixes-from-1.4.5.patch \
                   file://0002-appsrc-duration-query-support-based-on-the-size-prop.patch \
                   file://0003-WKIT-379-playbin-backported-stability-changes-from-upstream.patch \
                   file://0004-appsrc-backported-reset-eos-after-flush-from-gst-1.6.3.patch "
