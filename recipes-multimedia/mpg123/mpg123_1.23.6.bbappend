do_install_append_morty() {
        rm -f ${D}${libdir}/libout123.so.*
        rm -f ${D}${bindir}/out123 ${D}${bindir}/mpg123
}
