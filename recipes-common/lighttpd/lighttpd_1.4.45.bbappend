FILESEXTRAPATHS_prepend := "${THISDIR}/lighttpd_1.4.45:"

SRC_URI_append = " file://bind_lighttpd-1.4.45.patch"
SRC_URI_append = " file://lighttpd-md4-compilation-error-fix.patch"

CFLAGS_append_broadband = " -DSO_BINDTODEVICE"

SYSTEMD_SERVICE_${PN}_broadband = ""
INITSCRIPT_NAME_broadband = ""
INITSCRIPT_PARAMS_broadband = ""

FILES_${PN} += "${systemd_unitdir}/system/lighttpd.service"

RDEPENDS_${PN} += " \
    lighttpd-module-auth \
    lighttpd-module-authn-file \
    lighttpd-module-fastcgi \
    lighttpd-module-proxy \
"
