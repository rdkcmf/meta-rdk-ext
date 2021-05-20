FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append_broadband = " file://RDKCENTRAL_XDNS.patch"
SRC_URI_append_broadband = " file://RDKCENTRAL_XDNS_DualStack.patch"
SRC_URI_append_broadband = " file://RDKCENTRAL_XDNS_FixErrnoCorruption.patch"

CFLAGS += " -DNO_INOTIFY"

do_install_append() {
    sed -i -- 's/listen-address=127.0.0.1/#listen-address=127.0.0.1/g' ${D}${sysconfdir}/dnsmasq.conf
    sed -i -- 's/bind/#Remove this statement/g' ${D}${sysconfdir}/dnsmasq.conf
}
