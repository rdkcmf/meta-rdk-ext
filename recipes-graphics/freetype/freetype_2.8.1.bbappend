PACKAGECONFIG = "pixmap"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://freetype_null_dereference.patch"
