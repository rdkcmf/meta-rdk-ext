FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append_mipsel = "\
  file://0001-Allow-property-indexes-to-refer-to-slots-inside-the-.patch \
  file://0002-Unbreak-MIPS-part-a-bit.patch \
"

