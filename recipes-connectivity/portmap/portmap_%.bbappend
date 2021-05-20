FILESEXTRAPATHS_append := ":${THISDIR}/${PN}:"

inherit systemd

SRC_URI_append_hybrid = " file://portmap.service"

do_install_append_hybrid() {
     install -d ${D}${systemd_unitdir}/system
     install -m 0644 ${WORKDIR}/portmap.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${sysconfdir}/* ${systemd_unitdir}/system/portmap.service"

SYSTEMD_SERVICE_${PN}_hybrid = "portmap.service"




