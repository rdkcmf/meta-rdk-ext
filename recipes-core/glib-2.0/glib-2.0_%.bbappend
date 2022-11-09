FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
           file://fix_pollfd_dispatch.patch \
           "

#           file://log_conntion_failures.patch \
#

PTEST_ENABLED = "${@bb.utils.contains('DISTRO_FEATURES', 'benchmark_enable', '1', '0', d)}"
inherit ptest-package-deploy
PKG_glib-2.0="libglib-2.0"
