FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove = " file://130-fingerprint-dhcp-lease-file.patch \
                           file://client_notify.patch \
                         "
SRC_URI_append = " file://dnsmasq-2.81-client-notify-new.patch \
                 file://0001-dnsmasq-CVE-fixes-2.83.patch \
                 "
                 
SRC_URI_append_broadband = " file://dnsmasq-2.81-fingerprint-dhcp-lease-file.patch \
			   file://dnsmasq-2.81-lease-duplicate-hostnames.patch \  
			   file://dnsmasq-2.81-XDNS-secondary-XDNS-feature-zombie-fix.patch \
			   file://dnsmasq-2.81-XDNS-log-protect-browsing-MultiProfile.patch \
                           file://dnsmasq-2.81-RDKCENTRAL_XDNS_Refactor.patch \
                           "
 
do_install_append() {
    sed -i -- 's/listen-address=127.0.0.1/#listen-address=127.0.0.1/g' ${D}${sysconfdir}/dnsmasq.conf
    sed -i -- 's/bind/#Remove this statement/g' ${D}${sysconfdir}/dnsmasq.conf
}

