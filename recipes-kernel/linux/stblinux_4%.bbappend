FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI_append = "${@bb.utils.contains('DISTRO_FEATURES', 'cgroup',' file://cgroup_ext.cfg ',' ',d)}"
SRC_URI_append = "${@bb.utils.contains('DISTRO_FEATURES', 'seccomp',' file://seccomp.cfg ',' ',d)}"
SRC_URI_append = " ${@bb.utils.contains("DISTRO_FEATURES", "apparmor", " file://apparmor.cfg", "" ,d)}"
