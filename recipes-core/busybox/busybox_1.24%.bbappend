FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
VERSION_PATCHES = "\
   file://busybox-1.24-iproute.patch \
   file://busybox-pinginterval-mdev-support.patch \
   file://010-networking-fix-uninitialized-memory-when-displaying-.patch \
"

VERSION_PATCHES_append_client = " file://busybox-1.24-udhcp-trigger-milestones.patch"

# timeout: fix arguments to match coreutils
SRC_URI += " \
             file://0001-timeout-fix-arguments-to-match-coreutils.patch \
           "

do_install_append_morty() {
   rm -rf ${D}${base_sbindir}/runlevel
}

FILES_${PN} += "/sbin"
