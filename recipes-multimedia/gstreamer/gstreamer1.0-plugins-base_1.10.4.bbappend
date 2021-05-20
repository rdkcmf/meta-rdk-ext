FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0001-XRE-12082-Reset-eos-on-appsrc-on-send-flush-event.patch \
                   file://0002-appsrc-handle-duration-query-only-if-the-property-wa.patch \
                   file://0003-matroska-demux-support-for-parsing-HDR-plugin-base.patch \
                   file://make43.patch \
                   file://0004-Fix-linking-error-in-libwebrtc-wpe-2.22.patch \
                   file://0005-decodebin3-continue-parsebin-autoplug-if-requested-b.patch \
                   file://0006-urisourcebin-Call-do_async_done-when-source-state-ch.patch \
"
