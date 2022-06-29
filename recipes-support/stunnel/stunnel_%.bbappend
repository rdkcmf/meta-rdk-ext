FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

inherit systemd syslog-ng-config-gen
SYSLOG-NG_FILTER_hybrid = "stunnel"
SYSLOG-NG_SERVICE_stunnel_hybrid = "stunnel.service"
SYSLOG-NG_DESTINATION_stunnel_hybrid = "stunnel.log"
SYSLOG-NG_LOGRATE_stunnel_hybrid = "medium"

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

