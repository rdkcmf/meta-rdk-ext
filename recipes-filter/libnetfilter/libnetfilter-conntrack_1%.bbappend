FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI_append = " file://bin.patch \
                 "
DEBIAN_NOAUTONAME_${PN} = "1"
