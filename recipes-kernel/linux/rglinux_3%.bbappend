FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:${THISDIR}/kernel_3.12:"

SRC_URI_append = "${@bb.utils.contains('DISTRO_FEATURES', 'cgroup',' file://cgroup_ext.cfg ',' ',d)}"
SRC_URI_append = "\
                  file://0001-selinux-update-netlink-socket-classes.patch \
                  file://0001-ipv4-try-to-cache-dst_entries-which-would-cause-a-re.patch \
                  file://ambient-capabilities-kernel-3.12.patch  \
                  file://fix-typo-security-task-prctl.patch \
                 "
