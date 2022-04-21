inherit systemd syslog-ng-config-gen
SYSLOG-NG_FILTER_client += "bluetooth"
SYSLOG-NG_SERVICE_bluetooth_client += "bluetooth.service"
SYSLOG-NG_DESTINATION_bluetooth_client = "bluez.log"
SYSLOG-NG_LOGRATE_bluetooth_client = "medium"

# Remapping default localstatedir which has a value /var to /opt (persistent memory) across boxes
# to store bluetooth device and runtime operations data across STB power cycles
EXTRA_OECONF_append_hybrid += " --localstatedir=/opt"
EXTRA_OECONF_append_client += " --localstatedir=/opt"

EXTRA_OECONF_append_broadband += " --localstatedir=/opt/secure"

PACKAGECONFIG_append = " experimental"
EXTRA_OECONF += " --with-systemdsystemunitdir=${systemd_unitdir}/system"

RDEPENDS_${PN} += "${PN}-noinst-tools"
RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append() {
    mkdir -p ${D}${includedir}/bluetooth/audio/
    install -m 0644 ${S}/profiles/audio/a2dp-codecs.h ${D}${includedir}/bluetooth/audio/
    install -m 0644 ${S}/lib/uuid.h ${D}${includedir}/bluetooth/
    install -c -m 644 ${S}/src/main.conf  ${D}${sysconfdir}/bluetooth/main.conf
    #rm  ${D}${bindir}/bdaddr
    rm  ${D}${bindir}/avinfo
    rm  ${D}${bindir}/avtest
    rm  ${D}${bindir}/scotest
    rm  ${D}${bindir}/amptest
    rm  ${D}${bindir}/hwdb
    rm  ${D}${bindir}/hcieventmask
    rm  ${D}${bindir}/hcisecfilter
    rm  ${D}${bindir}/btinfo
    rm  ${D}${bindir}/btattach
    rm  ${D}${bindir}/btsnoop
    rm  ${D}${bindir}/btproxy
    rm  ${D}${bindir}/btiotest
    rm  ${D}${bindir}/mcaptest
    rm  ${D}${bindir}/cltest
    rm  ${D}${bindir}/oobtest
    rm  ${D}${bindir}/seq2bseq
    rm  ${D}${bindir}/ibeacon
    rm  ${D}${bindir}/btgatt-client
    rm  ${D}${bindir}/btgatt-server
    rm  ${D}${bindir}/gatt-service
    rm  ${D}${bindir}/iapd
}

do_install_append_hybrid() {
    mkdir -p ${D}${sysconfdir}/bluetooth/
    install -c -m 644 ${S}/src/main.conf  ${D}${sysconfdir}/bluetooth/main.conf
}


do_install_append_client() {
    mkdir -p ${D}${sysconfdir}/bluetooth/
    install -c -m 644 ${S}/src/main.conf  ${D}${sysconfdir}/bluetooth/main.conf
}

SYSTEMD_SERVICE_${PN} = "bluetooth.service"
SYSTEMD_AUTO_ENABLE = "enable"

