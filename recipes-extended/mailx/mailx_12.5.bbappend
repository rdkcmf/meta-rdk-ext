do_install_append() {
        rm -rf ${D}${sysconfdir}/nail.rc
        rm -rf ${D}${bindir}
}

FILES_${PN}_remove += "${sysconfdir}/nail.rc ${bindir}"

