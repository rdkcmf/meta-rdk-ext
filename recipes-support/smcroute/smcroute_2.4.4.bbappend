
inherit systemd
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-Change-config-file-dir-from-etc-to-opt.patch"

do_configure_prepend() {
    export PKG_CONFIG=${STAGING_DIR_NATIVE}${bindir}/pkg-config
}

do_compile_prepend() {
    install -d ${B}/lib
}

do_install_append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${B}/smcroute.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} += "smcroute.service"
