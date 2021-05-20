FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RDEPENDS_${PN} += " \
    libpcreposix \
    pcregrep \
    lighttpd-module-cgi \
    lighttpd-module-redirect \
    lighttpd-module-setenv \
    lighttpd-module-ssi \
    lighttpd-module-access \
    lighttpd-module-accesslog \
    lighttpd-module-rewrite \
    lighttpd-module-secdownload \
	"
