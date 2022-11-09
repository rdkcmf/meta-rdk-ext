FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://ptest-summary.sh "
SRC_URI_append = " file://ptest-diff.sh "

do_install_append () {
        install -d ${D}/${bindir}
        install -m 0755 ${WORKDIR}/ptest-summary.sh ${D}${bindir}/
        install -m 0755 ${WORKDIR}/ptest-diff.sh ${D}${bindir}/
}

RDEPENDS_${PN} += "bash"
FILES_${PN} += "${bindir}"
