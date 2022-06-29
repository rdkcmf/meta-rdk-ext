SUMMARY = "Simple logging library for rdklogger"
HOMEPAGE = "https://github.com/Comcast/cimplog"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS = "rdk-logger log4c"

SRCREV = "8b5c60f3930aa287121edd40c97915f692426a61"
SRC_URI = "git://github.com/Comcast/cimplog.git"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"
CFLAGS_append += " ${@bb.utils.contains('DISTRO_FEATURES', 'bci', '', '-DFEATURE_SUPPORT_ONBOARD_LOGGING',d)}"
inherit pkgconfig cmake
EXTRA_OECMAKE += "-DRDK_LOGGER=ON -DBUILD_TESTING=OFF -DBUILD_YOCTO=true"
EXTRA_OECMAKE_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'bci', '', ' -DFEATURE_SUPPORT_ONBOARD_LOGGING=true',d)}"

ASNEEDED_hybrid = ""
ASNEEDED_client = ""
