FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
           file://fix_pollfd_dispatch.patch \
           "

#           file://log_conntion_failures.patch \
#
