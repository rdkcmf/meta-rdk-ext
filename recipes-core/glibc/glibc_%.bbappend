
TUNE_CCARGS_append_krogoth = " -fomit-frame-pointer"
TUNE_CCARGS_append_morty = " -fomit-frame-pointer"

do_install_append_morty() {
        rm -f ${D}${bindir}/gencat
        rm -f ${D}${bindir}/getent
        rm -f ${D}${bindir}/getconf
        rm -f ${D}${bindir}/iconv
}

