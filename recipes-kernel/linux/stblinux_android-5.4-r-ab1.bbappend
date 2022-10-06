FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"
SRC_URI_append = " ${@bb.utils.contains("DISTRO_FEATURES", "benchmark_enable", " file://kall_syms.patch", "" ,d)}"
