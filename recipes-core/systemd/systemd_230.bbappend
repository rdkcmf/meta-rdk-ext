SRC_URI += " \
    file://journalctl-230.patch \
    file://systemd230-journalctl-remove-noentries-log.patch \
    file://10-ubi-device-systemd.rules \
"

## The below patches are needed to build systemd V230 with glibc V2.31 on dunfell(Yocto 3.1)
SRC_URI_append_dunfell = " file://0001-memfd-patch-for-latest-version-of-glibc.patch \
            file://0002-Remove-include-of-xlocale-header.patch \
            file://0003-Remove-MS-constants-from-missing-header-file.patch \
            file://0001-nss-util-silence-warning-about-deprecated-RES_USE_IN.patch \
            file://99-default.preset \
            "


EXTRA_OECONF += " --enable-polkit=no"
PACKAGECONFIG_remove = "pam"
FILES_${PN} += "${sysconfdir}/udev/rules.d/10-ubi-device-systemd.rules"

do_install_append() {
    rm -rf ${D}${sysconfdir}/resolv.conf
    sed -i '/After=swap.target/d' ${D}${systemd_unitdir}/system/tmp.mount

    rm -rf ${D}${base_libdir}/systemd/system/ldconfig.service
    rm -rf ${D}${base_libdir}/systemd/system/sysinit.target.wants/ldconfig.service

    # disable LLMNR queries
    if [ -f  ${D}${sysconfdir}/systemd/resolved.conf ]; then
        sed -i '/LLMNR/c\LLMNR=no' ${D}${sysconfdir}/systemd/resolved.conf
    fi
}

do_install_append_client() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/10-ubi-device-systemd.rules ${D}${sysconfdir}/udev/rules.d/    
    rm -rf ${D}${base_libdir}/systemd/systemd-update-done
    rm -rf ${D}${base_libdir}/systemd/system/systemd-update-done.service
    rm -rf ${D}${base_libdir}/systemd/system/sysinit.target.wants/systemd-update-done.service
    sed -i -e 's/systemd-update-done.service//g' ${D}${systemd_unitdir}/system/systemd-journal-catalog-update.service
    sed -i -e 's/systemd-update-done.service//g' ${D}${systemd_unitdir}/system/systemd-hwdb-update.service
}

do_install_append_hybrid() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/10-ubi-device-systemd.rules ${D}${sysconfdir}/udev/rules.d/    
    sed -i -e 's/systemd-update-done.service//g' ${D}${systemd_unitdir}/system/systemd-hwdb-update.service
}

do_install_append_broadband() {
    rm -rf ${D}${libdir}/tmpfiles.d/home.conf
}

do_install_append_dunfell() {
    install -Dm 0644 ${WORKDIR}/99-default.preset ${D}${systemd_unitdir}/system-preset/99-default.preset
}

FILES_${PN}-extra-utils_remove = "\
                        ${bindir}/systemd-cgls \
                        ${bindir}/systemd-cgtop \
"
