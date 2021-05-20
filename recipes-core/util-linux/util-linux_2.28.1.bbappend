do_install_append_morty () {
        rm -f ${D}${bindir}/lsipc
        rm -f ${D}${bindir}/lslogins
        rm -f ${D}${bindir}/lsns
}
