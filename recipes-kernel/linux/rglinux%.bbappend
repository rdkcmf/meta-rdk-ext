FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI_append = "\
                  file://nfsdisable.cfg \
                 "
SRC_URI_append_rdkzram = "\
                         file://zram.cfg \
                         "
SRC_URI_append = " ${@bb.utils.contains("DISTRO_FEATURES", "apparmor", " file://apparmor.cfg", "" ,d)}"
