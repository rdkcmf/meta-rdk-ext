
EXTRA_OECONF_append_class-target = " --disable-testio-debug --disable-debugfs --disable-imager \
                "${@bb.utils.contains('DISTRO_FEATURES', 'build_for_sky', '', 'disable_resizer',d)}" \
                --disable-tls --disable-uuidd \
                 --without-libiconv-prefix --without-libintl-prefix \
                "
EXTRA_OECONF_remove_class-target = "${@bb.utils.contains('DISTRO_FEATURES', 'storage_emmc', '--disable-debugfs', '',d)}"
###Added install_append task to move e2initrd_helper file from libdir to bindir as per FHS compliance#######

do_install_append(){
        install -d ${D}${bindir}
        mv ${D}${libdir}/e2initrd_helper ${D}${bindir}
}
