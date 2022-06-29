FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI_append = "\
                  file://nfsdisable.cfg \
                 "
SRC_URI_append_rdkzram = "\
                         file://zram.cfg \
                         "
SRC_URI_append = " ${@bb.utils.contains("DISTRO_FEATURES", "apparmor", " file://apparmor.cfg", "" ,d)}"
SRC_URI_append = " file://disable_auditsyscall.patch"
SRC_URI_remove_pacexf3 = " ${@bb.utils.contains("DISTRO_FEATURES", "apparmor", " file://apparmor.cfg", "" ,d)}"
SRC_URI_remove_pacexf3 = " file://disable_auditsyscall.patch"
SRC_URI_remove_pacexf3-bci = " ${@bb.utils.contains("DISTRO_FEATURES", "apparmor", " file://apparmor.cfg", "" ,d)}"
SRC_URI_remove_pacexf3-bci = " file://disable_auditsyscall.patch"