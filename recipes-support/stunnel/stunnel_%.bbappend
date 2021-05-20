FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

inherit systemd

DEPENDS_append_morty = " systemd "
DEPENDS_append_daisy = " systemd "

SRC_URI += "file://stunnel.service"

do_install_append() {

        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/stunnel.service ${D}${systemd_unitdir}/system

        rm -rf ${D}${sysconfdir}/stunnel
}

FILES_${PN} += "${systemd_unitdir}/system/*"
SYSTEMD_SERVICE_${PN} = "stunnel.service"

