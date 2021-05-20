SUMMARY = "RDK SSA Libraries and artifacts"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=19a2b3c39737289f92c7991b16599360"

SRC_URI = "${RDK_COMPONENTS_ROOT_GIT}/rdkssa/generic;protocol=${RDK_GIT_PROTOCOL};branch=${RDK_GIT_BRANCH};name=rdk-oss-ssa"

PV = "${RDK_RELEASE}"
SRCREV_rdk-oss-ssa = "${AUTOREV}"
PROVIDES = "rdk-oss-ssa"
RPROVIDES_${PN} = "rdk-oss-ssa"
SRCREV_FORMAT = "rdk-oss-ssa"
S = "${WORKDIR}/git"

DEPENDS = " ecryptfs-utils keyutils"

inherit pkgconfig autotools systemd

INCLUDE_DIRS = " \
    -I${STAGING_INCDIR} \
   "

CFLAGS += "${INCLUDE_DIRS} "
CPPFLAGS += " ${INCLUDE_DIRS} "
LDFLAGS += " -pthread -ldl"

#By default, RDKSSA Unit Test cases are disabled.
#Use "RDKSSA_UT_ENABLED=yes" to enable the RDKSSA Unit test cases
export RDKSSA_UT_ENABLED="no"

do_install_prepend() {

    install -d ${D}${includedir}
    install -D -m 0644 ${S}/ssa_top/ssa_oss/ssa_common/rdkssa.h ${D}${includedir}/

    install -d ${D}${systemd_unitdir}/system
    install -D -m 0644 ${S}/ssa_top/ssa_oss/ssa_common/providers/Mount/scripts/rdk-oss-ssa-ecfsinit.service ${D}${systemd_unitdir}/system/rdk-oss-ssa-ecfsinit.service

    install -d ${D}${bindir}
    install -D -m 0755 ${S}/ssa_top/ssa_oss/ssa_common/providers/Mount/scripts/ecfsMount ${D}${bindir}/

    install -d ${D}${sysconfdir}
    install -D -m 0644 ${S}/ssa_top/ssa_oss/ssa_common/providers/Mount/scripts/ecfs-mount-sample-dummy-key ${D}${sysconfdir}/

}

SYSTEMD_SERVICE_${PN} = " rdk-oss-ssa-ecfsinit.service"
FILES_${PN}_append = " ${systemd_unitdir}/system/*"

FILES_${PN} += "${bindir}/*"
FILES_${PN} += "${base_libdir}/*"
FILES_${PN} += "${sysconfdir}/*"


