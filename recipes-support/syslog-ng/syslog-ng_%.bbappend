FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "${@bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', ' file://syslog-ng.service ', '', d)}"

RDEPENDS_${PN}_remove = "gawk"
inherit update-alternatives

RDEPENDS_${PN}_append_dunfell = " busybox"

RREPLACES_${PN}  += "busybox-syslog sysklogd rsyslog"

python () {
    if bb.utils.contains('DISTRO_FEATURES', 'sysvinit', True, False, d):
        pn = d.getVar('PN', True)
        sysconfdir = d.getVar('sysconfdir', True)
        d.appendVar('ALTERNATIVE_%s' % (pn), ' syslog-init')
        d.setVarFlag('ALTERNATIVE_PRIORITY', 'syslog-init', '200')
        d.setVarFlag('ALTERNATIVE_LINK_NAME', 'syslog-init', '%s/init.d/syslog' % (sysconfdir))

    if bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d):
        pn = d.getVar('PN', True)
        d.appendVar('ALTERNATIVE_%s' % (pn), ' syslog-service')
        d.setVarFlag('ALTERNATIVE_LINK_NAME', 'syslog-service', '%s/systemd/system/syslog.service' % (d.getVar('sysconfdir', True)))
        d.setVarFlag('ALTERNATIVE_TARGET', 'syslog-service', '%s/system/${BPN}.service' % (d.getVar('systemd_unitdir', True)))
}


EXTRA_OECONF += " ${@bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', \
    ' --disable-http \
     --disable-json \
     --disable-smtp \
     --disable-snmp \
     --disable-amqp \
     --disable-ipv6 \
     --disable-redis \
     --disable-riemann \
     --disable-snmp-dest \
     --disable-spoof-source \
     --disable-sql \
     --disable-ssl \
     --disable-sun-door \
     --disable-sun-streams  \
     --disable-tcp-wrapper ' \
    , '', d)}"

do_install_append() {

    if ${@bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/${BPN}
        install -d ${D}${systemd_unitdir}/system
        install -m 644 ${WORKDIR}/syslog-ng.service  ${D}${systemd_unitdir}/system

        #Remove the syslog-ng@default.service link from multiuser target as we don't use it. 
        rm -f ${D}${systemd_unitdir}/system/multi-user.target.wants/${BPN}@default.service
    fi

}

PACKAGE_BEFORE_PN += "${@bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', '${PN}-extras', '', d)}"
FILES_${PN}-extras = "${libdir}/${PN}/loggen \
                     ${sbindir}/syslog-ng-debun \
                     ${bindir}/dqtool \
                     ${bindir}/loggen \
                     ${bindir}/pdbtool \
                     ${bindir}/persist-tool \
                     ${bindir}/update-patterndb \
                     ${libdir}/syslog-ng/libafsocket.so \
                     ${libdir}/syslog-ng/libcryptofuncs.so \
                     ${libdir}/syslog-ng/libadd-contextual-data.so \
                     ${libdir}/syslog-ng/libafprog.so \
                     ${libdir}/syslog-ng/libafstomp.so \
                     ${libdir}/syslog-ng/libafuser.so \
                     ${libdir}/syslog-ng/libappmodel.so \
                     ${libdir}/syslog-ng/libcef.so \
                     ${libdir}/syslog-ng/libconfgen.so \
                     ${libdir}/syslog-ng/libcsvparser.so \
                     ${libdir}/syslog-ng/libdbparser.so \
                     ${libdir}/syslog-ng/libdisk-buffer.so \
                     ${libdir}/syslog-ng/libexamples.so \
                     ${libdir}/syslog-ng/libgraphite.so \
                     ${libdir}/syslog-ng/libkvformat.so \
                     ${libdir}/syslog-ng/liblinux-kmsg-format.so \
                     ${libdir}/syslog-ng/libmap-value-pairs.so \
                     ${libdir}/syslog-ng/libpseudofile.so \
                     ${libdir}/syslog-ng/libsnmptrapd-parser.so \
                     ${libdir}/syslog-ng/libstardate.so \
                     ${libdir}/syslog-ng/libsystem-source.so \
                     ${libdir}/syslog-ng/libtags-parser.so \
                     ${libdir}/syslog-ng/libtfgetent.so \
                     ${libdir}/syslog-ng/libtimestamp.so \
                     ${libdir}/syslog-ng/libxml.so \
                     "

pkg_postinst_${PN}() {
    version="${@d.getVar('PV', True)}"
    if [ -n "$version" -a -n "$D" -a -d "$D" ]; then
        echo "${version}" > $D${sysconfdir}/syslog-ng/.version
    fi
}

SYSTEMD_SERVICE_${PN}_remove += "${@bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', 'syslog-ng@.service', '', d)}"
SYSTEMD_SERVICE_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', 'syslog-ng.service', '', d)}"
FILES_${PN} += " ${systemd_unitdir}/system/* "

FILES_${PN}-jconf = ""

#Override the libs files in case of syslog-ng distro is enabled.
FILES_${PN}-libs = " ${@bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', '${libdir}/libsyslog-ng-*.so*', '${libdir}/${BPN}/*.so ${libdir}/libsyslog-ng-*.so*', d)}"

FILES_${PN} += "${datadir}/${BPN}/include/scl/cim ${datadir}/${BPN}/include/scl/elasticsearch ${datadir}/${BPN}/include/scl/ewmm  ${datadir}/${BPN}/include/scl/graylog2 ${datadir}/${BPN}/include/scl/loggly ${datadir}/${BPN}/include/scl/logmatic ${datadir}/${BPN}/tools/merge-grammar.pl ${libdir}/${BPN}/libdate.so"

