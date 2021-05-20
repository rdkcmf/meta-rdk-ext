FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += " \
   file://viconsole.cfg \
   file://rdkb.cfg \
   file://shasum.cfg \
   file://tftp.cfg \
   file://netstat.cfg \
   file://tdk-needed-tools.cfg \
   file://telnet.cfg \
   file://date.cfg \
   file://ipneighbor.cfg \
   file://top.cfg \
   file://archival.cfg \
   file://traceroute.cfg \
   file://blkid.cfg \
   file://udhcp.patch \
   file://0001-networking-add-ip-neigh-command.patch \
   file://ip6_neigh_show_crash.patch \
   ${VERSION_PATCHES} \
   "

SRC_URI_remove_dunfell += " \
   file://udhcp.patch \
   file://0001-networking-add-ip-neigh-command.patch \
   file://ip6_neigh_show_crash.patch \
   "
SRC_URI_append_rpi = " \
   file://nice.cfg \
   "
SRC_URI_remove_broadband += " \
   file://blkid.cfg \
   "
SRC_URI_append_daisy = " file://devmem.cfg "
SRC_URI_append_morty = " file://enable_ps_wide.cfg "
SRC_URI_append_broadband = " ${@bb.utils.contains('DISTRO_FEATURES', 'dunfell', ' file://enable_ps_wide.cfg ','',d)}"
SRC_URI_append_dunfell = " file://enable_ar.cfg"
VERSION_PATCHES ?= ""

SRC_URI_append_hybrid += " \
        file://50default \
"

SRC_URI_append_client += " \
        file://50default.client \
"
do_install_append_hybrid() {
        install -d ${D}${sysconfdir}/udhcpc.d
        install -m 0755 ${WORKDIR}/50default ${D}${sysconfdir}/udhcpc.d
}


do_install_append_client() {
        install -d ${D}${sysconfdir}/udhcpc.d
        install -m 0755 ${WORKDIR}/50default.client ${D}${sysconfdir}/udhcpc.d/50default
}
