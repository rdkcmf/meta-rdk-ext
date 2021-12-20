FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-wpe_backend_libwpe_1.4.0.1_Gamepad-API.patch"

do_install_append() {
    ( cd ${D}${libdir}/pkgconfig && ln -sf wpe-1.0.pc wpe-0.2.pc )
}
