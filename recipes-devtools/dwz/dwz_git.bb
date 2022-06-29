LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYRIGHT_YEARS;md5=cca9473d676b14bc17a4782d5ca82d92 \
                    file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING.RUNTIME;md5=fe60d87048567d4fe8c8a0ed2448bcc8 \
                    file://COPYING3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://contrib/release/copyright-lines.awk;md5=213409382a32cbebe03598ef6eb62cce"

SRC_URI = "git://sourceware.org/git/dwz.git;protocol=https"

PV = "0.14+git${SRCPV}"
SRCREV = "22fe0df89cfff1985bac38d7e322a9aa4052ab70"

S = "${WORKDIR}/git"

DEPENDS = "elfutils"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install 'DESTDIR=${D}'
	install -d ${bindir}
        install -m755 ${D}/usr/bin/dwz ${bindir}/
}

BBCLASSEXTEND="native"
