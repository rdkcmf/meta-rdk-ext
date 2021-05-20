# Quagga Configure options added here
EXTRA_OECONF_append = " \
    --disable-vtysh \
    --disable-doc --disable-largefile \
    --enable-user=root --enable-group=root --enable-vty-group=root \
    --disable-bgpd --disable-watchquagga \
    --disable-ospfd --disable-ospf6d --disable-ospfclient \
    --localstatedir=/var/run \
"
FILESEXTRAPATHS_prepend:="${THISDIR}/files:"
SRC_URI_append = " file://cares-quagga-configure.patch " 
