require dwarf.inc

do_install() {
    install -d ${D}${libdir} ${D}${includedir}/libdwarf

    install -m 0755 ${B}/libdwarf/.libs/libdwarf.a ${D}${libdir}
    install -m 0644 ${S}/libdwarf/dwarf.h ${S}/libdwarf/libdwarf.h ${D}${includedir}/libdwarf
}

ALLOW_EMPTY_${PN} = "1"

BBCLASSEXTEND = "native"
