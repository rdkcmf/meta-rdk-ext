SUMMARY = "Program for providing universal TLS/SSL tunneling service"

DESCRIPTION = "SSL encryption wrapper between remote client and local (inetd-startable) or remote server."

SECTION = "net"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=dee9eec492805215d84978df86514c03"

DEPENDS += "openssl"

SRC_URI = "ftp://ftp.stunnel.org/stunnel/archive/5.x/${BP}.tar.gz"

SRC_URI[md5sum] = "4125b7c7f0f8c46266b4fa245ee8cda6"
SRC_URI[sha256sum] = "23acdb390326ffd507d90f8984ecc90e0d9993f6bd6eac1d0a642456565c45ff"

inherit autotools
EXTRA_OECONF += "--with-ssl='${STAGING_EXECPREFIXDIR}' --disable-fips"

PACKAGECONFIG ??= "tcp-wrappers"
PACKAGECONFIG[tcp-wrappers] = "--enable-libwrap,--disable-libwrap,tcp-wrappers"

COMPATIBLE_HOST_dunfell = "null"
