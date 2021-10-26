FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += " file://openssl-c_rehash.sh \
           "

do_install_append () {
        # Install a custom version of c_rehash that can handle sysroots properly.
        # This version is used for example when installing ca-certificates during
        # image creation.
        install -Dm 0755 ${WORKDIR}/openssl-c_rehash.sh ${D}${bindir}/c_rehash
        sed -i -e 's,/etc/openssl,${sysconfdir}/ssl,g' ${D}${bindir}/c_rehash
}
FILES_${PN} =+ " ${bindir}/c_rehash"
