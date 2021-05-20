SUMMARY = "Lightweight high-performance web server"
HOMEPAGE = "http://www.lighttpd.net/"
BUGTRACKER = "http://redmine.lighttpd.net/projects/lighttpd/issues"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=61fcb6632817d78f66982168f3e5d77e"

SECTION = "net"
DEPENDS = "sqlite3-native glib-2.0 zlib libpcre spawn-fcgi fcgi"
RDEPENDS_${PN} += " \
               lighttpd-module-access \
               lighttpd-module-accesslog \
               lighttpd-module-chunked \
               lighttpd-module-dirlisting \
               lighttpd-module-indexfile \
               lighttpd-module-staticfile \
               lighttpd-module-alias \
"

S = "${WORKDIR}/git"

SRCREV = "2c6497ec8c0e4a56a0dcbf85eccb36dec4f97c0d"

SRC_URI = "git://github.com/kraj/lighttpd-1.x.git;branch=lighttpd-1.5-dead \
        file://0001-src-Makefile.am-Dont-check-if-we-are-cross-compiling.patch \
        file://pkgconfig.patch \
        file://index.html.lighttpd \
        file://lighttpd.conf \
        file://lighttpd.service \
        "

CACHED_CONFIGUREVARS += "ac_cv_func_sendfile=yes"

EXTRA_OECONF = " \
             --without-bzip2 \
             --without-gdbm \
             --without-ldap \
             --without-lua \
             --without-memcache \
             --with-pcre \
             --without-webdav-props \
             --without-webdav-locks \
             --without-openssl \
             --disable-static \
"

inherit autotools pkgconfig update-rc.d gettext systemd

INITSCRIPT_NAME = "lighttpd"
INITSCRIPT_PARAMS = "defaults 70"

do_compile_prepend() {
	oe_runmake CC="${BUILD_CC}" -C src lemon
}

do_install_append() {
    install -d ${D}/var/www ${D}${sysconfdir}
    install -d ${D}/var/www/pages ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/lighttpd.conf ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/index.html.lighttpd ${D}/var/www/pages/index.html
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/lighttpd.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "/var/www/*"
FILES_${PN} += "${sysconfdir}/* ${systemd_unitdir}/system/lighttpd.service"

SYSTEMD_SERVICE_${PN} = "lighttpd.service"

CONFFILES_${PN} = "${sysconfdir}/lighttpd.conf"

PACKAGES_DYNAMIC += "^lighttpd-module-.*"

python populate_packages_prepend () {
    lighttpd_libdir = d.expand('${libdir}')
    do_split_packages(d, lighttpd_libdir, '^mod_(.*)\.so$', 'lighttpd-module-%s', 'Lighttpd module for %s', extra_depends='')
}
