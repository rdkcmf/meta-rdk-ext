DESCRIPTION = "miniUPnP daemon"
SECTION = "networking"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=a1ed15843ce66639bcf9f109cf247870"


inherit autotools gettext pkgconfig

DEPENDS = "iptables"
DEPENDS += "util-linux"

PR = "20191006"

S = "${WORKDIR}/miniupnpd-${PV}.${PR}"

SRC_URI = "http://miniupnp.free.fr/files/download.php?file=miniupnpd-${PV}.${PR}.tar.gz \
           file://makefile.patch \
"
SRC_URI[md5sum] = "340789edd49c113afe37834cc901a1e8"
SRC_URI[sha256sum] = "218fad7af31f3c22fb4c9db28a55a2a8b5067d41f5b38f52008a057a00d2206d"

do_compile() {
    cd ${S}
    ./genconfig.sh
    oe_runmake -f Makefile.linux
}

do_install() {
    install -d ${D}/${sbindir}
    install ${S}/miniupnpd ${D}/${sbindir}
    install -d ${D}/${sysconfdir}/${BPN}
    install ${S}/netfilter/iptables_init.sh ${D}/${sysconfdir}/${BPN}
    install ${S}/netfilter/iptables_removeall.sh ${D}/${sysconfdir}/${BPN}
    install ${S}/netfilter/ip6tables_init.sh ${D}/${sysconfdir}/${BPN}
    install ${S}/netfilter/ip6tables_removeall.sh ${D}/${sysconfdir}/${BPN}
    install -m 0644 -b ${S}/miniupnpd.conf ${D}/${sysconfdir}/${BPN}
    install -d ${D}/${sysconfdir}/init.d
    install ${S}/linux/miniupnpd.init.d.script ${D}/${sysconfdir}/init.d/miniupnpd 
}

COMPATIBLE_HOST_dunfell = "null"
