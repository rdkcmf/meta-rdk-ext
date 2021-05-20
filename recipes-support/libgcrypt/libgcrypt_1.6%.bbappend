FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
           file://fix-for-minidump-creation-libgcrypt-1.6.1.patch \
           "
