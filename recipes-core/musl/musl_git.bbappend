
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://execinfo.h"

do_install_append() {
    install -m 644 ${WORKDIR}/execinfo.h ${D}${includedir}/
}
