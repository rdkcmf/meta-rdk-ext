FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0001-isomp4-update-to-1_6_1.patch \
                   file://0002-isomp4-1_6_1-compile-fixes.patch \
                   file://0003-qtdemux-support-for-cenc-auxiliary-info-parsing-outs.patch \
                   file://0004-qtdemux-PIFF-box-detection-and-minimal-parsing-suppo.patch \
                   file://0005-Use-the-tfdt-decode-time-when-it-s-significantly-dif.patch \
                   file://0006-WKIT-393-Fix-gst-buffers-leak-on-parsing-encrypted-i.patch \
                   file://0007-XITHREE-7181-fix-handling-of-initial-segment.patch \
                   file://0008-WKIT-477-check-if-crypto_info-is-available-before-us.patch"

