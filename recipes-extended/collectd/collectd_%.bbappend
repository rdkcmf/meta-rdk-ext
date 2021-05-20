FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
        file://collectd.conf \
        file://update-mac-address.sh \
       "

PACKAGECONFIG[mysql] = "--with-libmysql,--with-libmysql=no,mysql5,"
PACKAGECONFIG[gcrypt] = "--with-libgcrypt=${STAGING_BINDIR_CROSS}/libgcrypt-config,--with-libgcrypt=no,libgcrypt,"
PACKAGECONFIG[yajl] = "--with-libyajl,--with-libyajl=no,yajl,"
PACKAGECONFIG[dns] = "--with-libpcap,--with-libpcap=no,libpcap,"
PACKAGECONFIG[curl] = "--with-libcurl,--with-libcurl=no,curl,"
PACKAGECONFIG[debug] = "--enable-debug,--disable-debug,,"

DEPENDS_remove = "rrdtool"

EXTRA_OECONF := " \
                ${FPLAYOUT} \
                --disable-perl --with-libperl=no --with-perl-bindings=no \
                --disable-notify_desktop \
"

do_install_append(){
    install -D -m 0644 ${WORKDIR}/collectd.conf ${D}${systemd_unitdir}/system/collectd.service.d/collectd.conf
    install -D -m 755 ${WORKDIR}/update-mac-address.sh  ${D}${base_libdir}/rdk/update-mac-address.sh
}

FILES_${PN} += "${systemd_unitdir}/system/collectd.service.d/collectd.conf"
FILES_${PN} += "${base_libdir}/rdk/update-mac-address.sh"
