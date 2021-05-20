
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

