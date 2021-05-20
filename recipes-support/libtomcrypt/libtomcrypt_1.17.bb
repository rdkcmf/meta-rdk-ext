DESCRIPTION = "tomcrypt"
HOMEPAGE = " http://www.libtom.net"
LICENSE = "DWTFYW"
LIC_FILES_CHKSUM ="file://${S}/LICENSE;md5=23e7e0a32e53a2b1d35f5fd9ef053402"
SRC_URI = "git://github.com/libtom/libtomcrypt.git;branch=develop"
SRCREV = "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"


S = "${WORKDIR}/git"
do_compile() {

        oe_runmake -f makefile
}
EXTRA_OEMAKE = "library"


do_install() {
	install -d ${D}${libdir}/
	install -d ${D}${includedir}/
        oe_libinstall -a libtomcrypt ${D}${libdir}/
        install -m 0644 ${S}/src/headers/*.h ${D}${includedir}/
}

inherit native
