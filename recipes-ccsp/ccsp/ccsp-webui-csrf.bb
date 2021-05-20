SUMMARY = "WebUI CSRF component"

HOMEPAGE = "https://github.com/mebjas/CSRF-Protector-PHP"

DEPENDS = "ccsp-common-library php chrpath-replacement-native"

include recipes-ccsp/ccsp/ccsp_common.inc

LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://licence.md;md5=4713ed2839d834a4ff42c4d742abcb81"

SRCREV = "88cfff18dbdc604d51c653f728da161b4ea1eb02"
SRC_URI = "git://github.com/mebjas/CSRF-Protector-PHP.git \
          file://0001-csrfprotector-php.patch \
	  file://csrfprotector.patch \
         "

PV = "git+${SRCPV}"
S = "${WORKDIR}/git/"

CFLAGS += " \
    -I${STAGING_INCDIR}/dbus-1.0 \
    -I${STAGING_LIBDIR}/dbus-1.0/include \
    -I${STAGING_INCDIR}/ccsp \
    -fPIC \
    "

LDFLAGS += " \
     -ldbus-1 \
     "

do_install () {
    # Config files and scripts
    install -d ${D}/usr/www/CSRF-Protector-PHP
    install -d ${D}/usr/www/CSRF-Protector-PHP/js
    install -d ${D}/usr/www/CSRF-Protector-PHP/libs
    install -d ${D}/usr/www/CSRF-Protector-PHP/libs/csrf

    install -m 755 ${S}/js/* ${D}/usr/www/CSRF-Protector-PHP/js
    install -m 755 ${S}/libs/*.php ${D}/usr/www/CSRF-Protector-PHP/libs
    install -m 755 ${S}/libs/csrf/*.php ${D}/usr/www/CSRF-Protector-PHP/libs/csrf
}

FILES_${PN} += "/usr/www/CSRF-Protector-PHP/js/*"
FILES_${PN} += "/usr/www/CSRF-Protector-PHP/libs/*"
FILES_${PN} += "/usr/www/CSRF-Protector-PHP/libs/csrf/*"
