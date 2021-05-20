FILESEXTRAPATHS_prepend := "${THISDIR}/lighttpd_1.4.39:"

DEPENDS_append = " zlib openssl"
EXTRA_OECONF_append = " --without-bzip2 --without-mysql --with-zlib --with-openssl"

SRC_URI_append = " file://bind_lighttpd-1.4.39.patch"
SRC_URI_append = " file://SSL_shutdown.patch"

CFLAGS_append_broadband = " -DSO_BINDTODEVICE"

SYSTEMD_SERVICE_${PN}_broadband = ""
INITSCRIPT_NAME_broadband = ""
INITSCRIPT_PARAMS_broadband = ""

FILES_${PN} += "${systemd_unitdir}/system/lighttpd.service"

RDEPENDS_${PN} += " \
    lighttpd-module-auth \
    lighttpd-module-fastcgi \
    lighttpd-module-proxy \
"
