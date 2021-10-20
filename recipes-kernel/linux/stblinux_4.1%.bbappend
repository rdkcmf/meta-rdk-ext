FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI_append = "\
                  file://0001-selinux-update-netlink-socket-classes.patch \
                  file://ambient-caps-rdkv-kernel-4.1.patch \
                  file://EMFILE_error_fix.patch \
                 "
