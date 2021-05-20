FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
VERSION_PATCHES = "\
   file://busybox-1.23-iproute.patch \
   file://busybox-pinginterval-mdev-support.patch \
"
# timeout: fix arguments to match coreutils
SRC_URI += " \
             file://0001-timeout-fix-arguments-to-match-coreutils.patch \
           "

