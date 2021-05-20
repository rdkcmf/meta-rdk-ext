FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += "file://procps.pc \
            file://ignore_pkill_requests_with_empty_pattern.patch"


do_install_append() {
        install -Dm644 ${S}/../procps.pc ${D}${libdir}/pkgconfig/procps.pc
}
