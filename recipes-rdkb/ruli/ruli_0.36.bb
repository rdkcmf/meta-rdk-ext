SUMMARY = "RULI stands for Resolver User Layer Interface It's a library	built on top of an asynchronous DNS stub resolver"

HOMEPAGE = "http://www.nongnu.org/ruli/"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

DEPENDS = "liboop"

SRC_URI += "ftp://ftp.gnome.org/mirror/gnu.org/savannah/ruli/ruli_${PV}.orig.tar.gz"

SRC_URI += "file://Makefile.patch"

SRC_URI[md5sum] = "e73fbfdeadddb68a703a70cea5271468"
SRC_URI[sha256sum] = "11d32def5b514748fbd9ea8c88049ae99e1bb358efc74eb91a4d268a3999dbfa"

S = "${WORKDIR}/${PN}-${PV}"

do_install() {
	install -d ${D}${includedir}/ruli
	install -d ${D}${libdir}
	install -m 0644 ${S}/src/ruli*.h ${D}${includedir}/ruli
	install -m 0644 ${S}/src/libruli.so ${D}${libdir}
	install -m 0644 ${S}/src/libruli.so.4 ${D}${libdir}
}

FILES_SOLIBSDEV = ""
SOLIBS = ".so"
INSANE_SKIP_${PN} += "dev-so"
FILES_${PN} += "${libdir}/libruli.so ${libdir}/libruli.so.4"
