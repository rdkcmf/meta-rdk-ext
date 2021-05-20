FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI_append = "${@bb.utils.contains('DISTRO_FEATURES', 'cgroup',' file://cgroup_ext.cfg ',' ',d)}"
SRC_URI_append = "\
                  file://0001-add-support-for-http-host-headers-cookie-url-netfilt.patch \
                  file://0001-selinux-update-netlink-socket-classes.patch \
                  file://gre.cfg \
                  file://mqueue.cfg \
                  file://nfsdisable.cfg \
                 "
