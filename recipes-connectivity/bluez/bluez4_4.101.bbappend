inherit systemd

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://audioconf.patch"

# Remapping default localstatedir which has a value /var to /opt (persistent memory) across boxes
# to store bluetooth device and runtime operations data across STB power cycles
export localstatedir="/opt"

EXTRA_OECONF += "--with-systemdunitdir=${systemd_unitdir}/system/"

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"

do_install_append() {
    mkdir -p ${D}${includedir}/bluetooth/audio/
    install -m 0644 ${S}/audio/a2dp-codecs.h ${D}${includedir}/bluetooth/audio/
    install -m 0644 ${S}/audio/ipc.h ${D}${includedir}/bluetooth/audio/
    install -m 0544 ${S}/test/bdaddr ${D}${sbindir}
}

SYSTEMD_SERVICE_${PN} = "bluetooth.service"
SYSTEMD_AUTO_ENABLE = "enable"
