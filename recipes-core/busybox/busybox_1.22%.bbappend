FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
VERSION_PATCHES = "\
   file://busybox-1.22-iproute.patch \
   file://CVE-2016-2148.patch \
   file://CVE-2016-6301.patch \
   file://busybox-1.22-pinginterval-mdev-support.patch \
"

VERSION_PATCHES_append_client = " file://busybox-1.22-udhcp-trigger-milestones.patch"
# timeout: fix arguments to match coreutils
SRC_URI += " \
             file://0001-timeout-fix-arguments-to-match-coreutils.patch \
           "
