FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

do_install_append() {

        rm -rf ${D}${sysconfdir}/stunnel
}


