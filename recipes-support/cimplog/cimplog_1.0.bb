SUMMARY = "Simple logging library for rdklogger"
HOMEPAGE = "https://github.com/Comcast/cimplog"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS = "rdk-logger log4c"

SRCREV = "8cf7bec93c138c89aa172d4930826deb23a8a658"
SRC_URI = "git://github.com/Comcast/cimplog.git"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"
CFLAGS_append += " ${@bb.utils.contains('DISTRO_FEATURES', 'bci', '', '-DFEATURE_SUPPORT_ONBOARD_LOGGING',d)}"
inherit pkgconfig cmake
EXTRA_OECMAKE += "-DRDK_LOGGER=ON -DBUILD_TESTING=OFF -DBUILD_YOCTO=true"
EXTRA_OECMAKE_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'bci', '', ' -DFEATURE_SUPPORT_ONBOARD_LOGGING=true',d)}"

HASBCI = "${@bb.utils.contains('DISTRO_FEATURES', 'bci', 'true', 'false',d)}"
do_install_append() {
   if [ ${HASBCI} = "false" ]; then
    install -d ${D}/etc
	touch ${D}/etc/ONBOARD_LOGGING_ENABLE
   fi
}

FILES_${PN} += " \
	${@bb.utils.contains("DISTRO_FEATURES", "bci", " ", "/etc/* ", d)} \
"

ASNEEDED_hybrid = ""
ASNEEDED_client = ""
