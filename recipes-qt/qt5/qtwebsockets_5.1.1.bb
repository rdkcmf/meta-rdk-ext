SUMMARY = "QtWebSockets"
SECTION = "qtapps"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "qtbase"

SRCREV = "${AUTOREV}"

PV .= "+git${SRCPV}"

SRC_URI = "git://${RDK_GIT}/rdk/components/opensource/qtwebsockets/generic;module=.;protocol=${RDK_GIT_PROTOCOL};branch=${RDK_GIT_BRANCH}"

S = "${WORKDIR}/git"

# this component doesn't build with -Wl,-as-needed, remove the flag for now
ASNEEDED = ""

require recipes-qt/qt5/qt5.inc

OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

ERROR_QA_remove_morty = "pkgconfig"

ERROR_QA_remove_krogoth = "pkgconfig"

do_configure_prepend () {
	rm -rf ${S}/examples
}
