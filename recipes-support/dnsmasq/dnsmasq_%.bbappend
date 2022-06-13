FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://addtruncatedmsgcaching.patch"
SRC_URI += "file://dnsmasq.service"

SRC_URI += "file://dns.conf"
SRC_URI += "file://130-fingerprint-dhcp-lease-file.patch"

CFLAGS += " -DNO_INOTIFY"
     
SRC_URI += "file://dnsmasqLauncher.sh"
SRC_URI_append_broadband += "file://dnsmasq_syslog_quiet.patch"

inherit syslog-ng-config-gen
SYSLOG-NG_FILTER = "dnsmasq"
SYSLOG-NG_SERVICE_dnsmasq = "dnsmasq.service"
SYSLOG-NG_DESTINATION_dnsmasq = "dnsmasq.log"
SYSLOG-NG_LOGRATE_dnsmasq = "low"

do_install_append() {
     install -d ${D}${base_libdir}/rdk
     install -m 0644 ${WORKDIR}/dnsmasq.service ${D}${systemd_unitdir}/system
     sed -i -- 's/#resolv-file=/resolv-file="\/etc\/resolv.dnsmasq"/g' ${D}/etc/dnsmasq.conf
     sed -i -- 's/#user=/user=root/g' ${D}/etc/dnsmasq.conf
     sed -i -- 's/#dhcp-leasefile=\/var\/lib\/misc\/dnsmasq.leases/dhcp-leasefile=\/tmp\/dnsmasq.leases/g' ${D}/etc/dnsmasq.conf
     touch ${D}${sysconfdir}/resolv.conf
     echo "nameserver 127.0.0.1" > ${D}${sysconfdir}/resolv.conf
     echo "options timeout:1" >> ${D}${sysconfdir}/resolv.conf
     echo "options attempts:2" >> ${D}${sysconfdir}/resolv.conf
     touch ${D}${sysconfdir}/resolv.dnsmasq
     install -m 0755 ${S}/../dnsmasqLauncher.sh ${D}${base_libdir}/rdk
}

do_install_append_hybrid() {
    install -D -m 0644 ${WORKDIR}/dns.conf ${D}${systemd_unitdir}/system/dnsmasq.service.d/dns.conf
}

do_install_append_client() {
    install -D -m 0644 ${WORKDIR}/dns.conf ${D}${systemd_unitdir}/system/dnsmasq.service.d/dns.conf
}

RDEPENDS_${PN} += "busybox"

FILES_${PN}_append = " ${sysconfdir}/resolv.conf \
                       ${sysconfdir}/resolv.dnsmasq \
                       ${base_libdir}/rdk/* \
                      "
FILES_${PN}_append_hybrid += " ${systemd_unitdir}/system/dnsmasq.service.d/dns.conf"
FILES_${PN}_append_client += " ${systemd_unitdir}/system/dnsmasq.service.d/dns.conf"
