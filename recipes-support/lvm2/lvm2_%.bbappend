do_install_append(){
        rm -rf ${D}${sysconfdir}/lvm
        rm -rf ${D}${sbindir}
}

do_install_append_morty(){
        rm -rf ${D}${base_libdir}/udev/rules.d/10-dm.rules
}

PACKAGECONFIG_remove = "udev"
FILES_${PN}_remove = "${sysconfdir}/lvm ${sbindir}"
RDEPENDS_${PN}_remove = "thin-provisioning-tools"
