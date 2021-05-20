FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "  file://rdkhooks.patch \
                    file://xre.conf \
                    file://lxc200_xretemplate.patch \
                 "

SRC_URI_append_broadband = "  file://lighttpdhook_makefile.patch \
			      file://lxc-net.patch \
                           "

SRC_URI_append_class-native = " file://lxc-create-native-fix.patch \
                                file://lxc200-create-on-multiuser-system.patch \
                              "

do_install_append () {
    if [ -f  ${D}/etc/default/lxc ]; then
        sed -i 's|'OPTIONS='|OPTIONS=\"-P /lxc\"|' ${D}/etc/default/lxc
    fi
}
do_install_append_hybrid () {
    install -d ${D}${systemd_unitdir}/system/lxc.service.d
    install -m 0666 ${WORKDIR}/xre.conf ${D}${systemd_unitdir}/system/lxc.service.d/

}

do_install_append_client () {
    install -d ${D}${systemd_unitdir}/system/lxc.service.d
    install -m 0666 ${WORKDIR}/xre.conf ${D}${systemd_unitdir}/system/lxc.service.d/
    sed -i -e 's/snmpd.service//g'  ${D}${systemd_unitdir}/system/lxc.service.d/xre.conf

}

FILES_${PN}_append_client = " ${systemd_unitdir}/system/lxc.service.d/xre.conf"
FILES_${PN}_append_hybrid = " ${systemd_unitdir}/system/lxc.service.d/xre.conf"

SYSTEMD_AUTO_ENABLE_broadband = "disable"
