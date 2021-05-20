
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

CACHED_CONFIGUREVARS = "ac_cv_lib_crypto_EVP_md5=no ac_cv_lib_crypto_AES_cfb128_encrypt=no"

DEPENDS += "openssl"
DEPENDS_append = " libpcap"

CFLAGS += "-DNETSNMP_USE_OPENSSL -DHAVE_LIBSSL -lssl -lcrypto"

EXTRA_OECONF += "--without-rpm --with-perl-modules=no --disable-embedded-perl \
                 --enable-mfd-rewrites --with-default-snmp-version=3 \
                 --with-logfile=/var/log/snmpd.log --with-persistent-directory=/var/net-snmp \
                 --with-out-mib-modules=ifTable \
                 --with-out-mib-modules=ifXTable \
                 --with-out-mib-modules=inetNetToMediaTable \
                 --with-out-mib-modules=snmpMIB \
                 --with-out-mib-modules=system \
                 --with-out-mib-modules=mibII/system_mib \
                 --with-out-mib-modules=mibII/sysORTable \
                 --with-out-mib-modules=host/hr_swrun \
                 --with-out-mib-modules=host/hr_swinst \
                 --with-out-mib-modules=host/hr_print \
                 --with-mib-modules=mibII/ipv6 \
                 --with-transports=UDPIPv6,DTLSUDP,TLSTCP \
                 --enable-ipv6 \
                 --with-security-modules=tsm \
                 --disable-scripts \
                 --disable-manuals \
"

SRC_URI += "file://netsnmp-fix-ipAddressTable-issue.patch \
"

SRC_URI_append_dunfell = " file://pciutils3.5.1_udev_leak.patch \
                         "

SRC_URI_append_broadband = " \
            file://double_free.patch  \
            file://snmp-crash.patch \
            file://CiscoXB3-2774.patch \
"

do_install_prepend() {
    rm -f ${D}/snmp/snmp_perl_trapd.pl
}

do_install_append() {
    rm ${D}${sysconfdir}/snmp/snmptrapd.conf
    rm ${D}${bindir}/agentxtrap
    rm ${D}${bindir}/encode_keychange
    rm ${D}${bindir}/net-snmp-create-v3-user
    rm ${D}${bindir}/snmpbulkget
    rm ${D}${bindir}/snmpbulkwalk
    rm ${D}${bindir}/snmpdelta
    rm ${D}${bindir}/snmpgetnext
    rm ${D}${bindir}/snmpnetstat
    rm ${D}${bindir}/snmpdf
    rm ${D}${bindir}/snmpstatus
    rm ${D}${bindir}/snmptable
    rm ${D}${bindir}/snmptest
    rm ${D}${bindir}/snmptls
    rm ${D}${bindir}/snmptrap
    rm ${D}${bindir}/snmpusm
    rm ${D}${bindir}/snmpvacm
    rm ${D}${datadir}/snmp/snmp_perl_trapd.pl
}

RDEPENDS_${PN}-server = "net-snmp-server-snmpd"
FILES_${PN}-client_remove = "${bindir}/agentxtrap ${bindir}/encode_keychange ${bindir}/net-snmp-create-v3-user ${bindir}/snmpbulkget ${bindir}/snmpbulkwalk ${bindir}/snmpdelta ${bindir}/snmpgetnext ${bindir}/snmpnetstat ${bindir}/snmpdf ${bindir}/snmpstatus ${bindir}/snmptable ${bindir}/snmptest ${bindir}/snmptls ${bindir}/snmptrap ${bindir}/snmpusm ${bindir}/snmpvacm ${datadir}/snmp/snmp_perl_trapd.pl"
