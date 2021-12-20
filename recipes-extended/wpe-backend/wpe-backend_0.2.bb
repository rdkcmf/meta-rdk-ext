SUMMARY = "WPE WebKit backend library"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "BSD-2-Clause & Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ae4db0d4b812334e1539cd5aa6e2f46"

DEPENDS += "libxkbcommon libinput"

PV = "0.2+git${SRCPV}"

#revision date: Nov 5, 2018
SRCREV = "b6753c535b5be0db330001ff13387c91d53b48bc"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEBackend.git;protocol=http;branch=master"
SRC_URI = "${BASE_URI}"

SRC_URI += "file://0001-Gamepad-API.patch"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PROVIDES += "libwpe"
RPROVIDES_${PN} += "libwpe"

do_configure_prepend() {
    mkdir -p ${STAGING_DATADIR}/cmake/Modules/
    cp ${S}/cmake/*.cmake ${STAGING_DATADIR}/cmake/Modules/
}

EXTRA_OECONF += " \
    -DWPE_BACKEND=libwpe-0.2.so \
    -DCMAKE_C_FLAGS=-D_GNU_SOURCE \
"

CFLAGS += " \
-D_GNU_SOURCE \
"
