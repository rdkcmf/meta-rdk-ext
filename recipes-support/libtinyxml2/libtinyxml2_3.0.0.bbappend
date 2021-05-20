FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append += " file://libtinyxml2.pc \
		    file://libtinyxml2_change.patch"

do_install_append_morty() {
        install -Dm644 ${B}/../libtinyxml2.pc ${D}${libdir}/pkgconfig/libtinyxml2.pc
}
