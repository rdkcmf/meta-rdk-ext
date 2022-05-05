FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://srt-fix-srto_linger-and-recv-buff-in-yocto-build.patch \
"
DEPENDS += "gstreamer1.0-plugins-base"

PACKAGECONFIG += " srtp srt dash smoothstreaming "
