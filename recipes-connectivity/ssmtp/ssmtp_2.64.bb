SUMMARY = "extremely simple MTA to get mail off the system to a mail hub"
HOMEPAGE = "http://packages.qa.debian.org/s/ssmtp.html"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0c56db0143f4f80c369ee3af7425af6e"

SRC_URI = "${DEBIAN_MIRROR}/main/s/${BPN}/${BPN}_${PV}.orig.tar.bz2 \
           file://ssmtp-bug584162-fix.patch \
           file://build-ouside_srcdir.patch \
           file://use-DESTDIR.patch \
"

SRC_URI[md5sum] = "65b4e0df4934a6cd08c506cabcbe584f"
SRC_URI[sha256sum] = "22c37dc90c871e8e052b2cab0ad219d010fa938608cd66b21c8f3c759046fa36"

inherit autotools

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'ipv6', '', d)}"

PACKAGECONFIG[ssl] = "--enable-ssl,--disable-ssl,openssl"
PACKAGECONFIG[ipv6] = "--enable-inet6,--disable-inet6"

EXTRA_OECONF += "--mandir=${mandir}"

EXTRA_OEMAKE = "GEN_CONFIG='/bin/true'"

LDFLAGS += "${@bb.utils.contains('PACKAGECONFIG', 'ssl', '-lssl -lcrypto', '', d)}"

do_compile_prepend_morty() {
        cp -rf ../ssmtp-2.64/*.c .
        cp -rf ../ssmtp-2.64/*.h .
}

do_install_append () {
    install -d ${D}${mandir}/
    mv ${D}${exec_prefix}/man/* ${D}${mandir}/
    rmdir ${D}${exec_prefix}/man
    ln -s ssmtp ${D}${sbindir}/sendmail
    ln -s ssmtp ${D}${sbindir}/newaliases
    ln -s ssmtp ${D}${sbindir}/mailq
}
TARGET_CC_ARCH += "${LDFLAGS}"
