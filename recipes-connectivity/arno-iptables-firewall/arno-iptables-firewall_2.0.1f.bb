SUMMARY = "IPTables based firewall scripts"
HOMEPAGE = "http://rocky.eld.leidenuniv.nl/joomla/index.php?option=com_content&view=article&id=45&Itemid=63"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://gpl_license.txt;md5=11c7b65c4a4acb9d5175f7e9bf99c403"

SRC_URI = "http://rocky.eld.leidenuniv.nl/arno-iptables-firewall/${BPN}_${PV}.tar.gz \
          "
SRC_URI[md5sum] = "7a7b5d448b0103d3c1bf6fe3187caf58"
SRC_URI[sha256sum] = "198848b8241b31f8498b43517af13879f55e00d17edfc6647ca48b43af2789a9"

S = "${WORKDIR}/${PN}_2.0.1f"

inherit systemd

do_install() {
	install -d ${D}${sysconfdir} ${D}${sbindir} ${D}${bindir} ${D}${systemd_unitdir}/system ${D}${sysconfdir}/init.d
        install -d ${D}${datadir}/arno-iptables-firewall ${D}${sysconfdir}/arno-iptables-firewall
	cp -a ${S}${sysconfdir}/arno-iptables-firewall ${D}${sysconfdir}/
	install -m 0755 ${S}${sysconfdir}/init.d/arno-iptables-firewall ${D}${bindir}
	install -m 0755 ${S}/bin/arno-iptables-firewall ${D}${sbindir}
	install -m 0755 ${S}/bin/arno-fwfilter ${D}${bindir}
        cp -a ${S}/share/arno-iptables-firewall/* ${D}${datadir}/arno-iptables-firewall
	cp -a ${S}/etc/arno-iptables-firewall/* ${D}${sysconfdir}/arno-iptables-firewall
	install -m 0644 ${S}/${systemd_unitdir}/system/arno-iptables-firewall.service ${D}${systemd_unitdir}/system
	sed -i -e 's%/usr/local/sbin%${bindir}%g' ${D}${systemd_unitdir}/system/arno-iptables-firewall.service
	sed -i -e 's%/usr/local/sbin%${sbindir}%g' ${D}${bindir}/arno-iptables-firewall
        sed -i -e 's%/usr/local%${exec_prefix}%g' ${D}${sysconfdir}/arno-iptables-firewall/firewall.conf
}

SYSTEMD_SERVICE_${PN} = "arno-iptables-firewall.service"
FILES_${PN} += "${systemd_unitdir}/system/arno-iptables-firewall.service"
