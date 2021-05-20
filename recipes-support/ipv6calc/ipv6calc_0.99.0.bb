
DESCRIPTION = "Recipe to build ipv6calc library"
HOMEPAGE = "http://pkgs.fedoraproject.org/lookaside/pkgs/ipv6calc"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=df7b533e1ebb65cf315510ab93b9313a \
                    file://LICENSE;md5=de494688e1a9c54e923deaf7b47beea7 \
                   "
SRC_URI = "https://www.deepspace6.net/ftp/pub/ds6/sources/ipv6calc/ipv6calc-${PV}.tar.gz"
SRC_URI[md5sum] = "8d645f83492b739e8def05dccee53897"
SRC_URI[sha256sum] = "fc7535b900339d75d9862b1c3d359304330f72e94a4cade539dfb714e533fdb5"

SRC_URI += "file://configure.patch"

inherit autotools pkgconfig

B = "${S}"
PACKAGECONFIG_dunfell ??= "nowarn"
PACKAGECONFIG[nowarn] = "--disable-compiler-warning-to-error"

RDEPENDS_ipv6calc = " bash perl"
do_install_append() {
	install -d ${D}${libdir}
	install -m 0755 ${S}/lib/libipv6calc.so.0.99.0 ${D}${libdir}
}

PACKAGES =+ "${PN}-main ${PN}-log"
FILES_${PN}-main = "${bindir}/ipv6calc"
FILES_${PN}-log = "${bindir}/ipv6logconv ${bindir}/ipv6loganon ${bindir}/ipv6logstats"
