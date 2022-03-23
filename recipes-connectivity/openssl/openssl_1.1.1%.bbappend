FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += " file://openssl-c_rehash.sh \
           "
#Disable unapproved cipher algorithms
EXTRA_OECONF += "no-camellia"
EXTRA_OECONF += "no-seed"
EXTRA_OECONF += "no-rc5"
EXTRA_OECONF += "no-md2"
EXTRA_OECONF += "no-md4"
EXTRA_OECONF += "no-mdc2"
EXTRA_OECONF += "no-ssl2"
EXTRA_OECONF += "no-ssl3"
EXTRA_OECONF += "no-err"
EXTRA_OECONF += "no-hw"
EXTRA_OECONF += "no-srp"
EXTRA_OECONF += "no-idea"
EXTRA_OECONF += "no-rc4"
EXTRA_OECONF += "no-aria"
EXTRA_OECONF += "no-sm4"
EXTRA_OECONF += "no-sm2"

#Temporary fix added for DELIA-54629
SRC_URI_append_hybrid = "${@bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', ' file://disable_sigalgs.patch', '', d)}"
EXTRA_OECONF_append_hybrid = "${@bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', ' no-tls1_3', '', d)}"

do_install_append () {
        # Install a custom version of c_rehash that can handle sysroots properly.
        # This version is used for example when installing ca-certificates during
        # image creation.
        install -Dm 0755 ${WORKDIR}/openssl-c_rehash.sh ${D}${bindir}/c_rehash
        sed -i -e 's,/etc/openssl,${sysconfdir}/ssl,g' ${D}${bindir}/c_rehash
}
FILES_${PN} =+ " ${bindir}/c_rehash"
