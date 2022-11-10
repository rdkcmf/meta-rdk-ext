SUMMARY = "WPE WebKit RDK backend"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "BSD-2-Clause & Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ab5b52d145a58f5fcc0e2a531e7a2370"

DEPENDS += "libwpe glib-2.0"

PV = "0.3+git${SRCPV}"

# Revision date: Aug 11 2022
SRCREV = "13a8f6a2182cd5d95b630f4c94917ac841231ffc"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEBackend-rdk.git;protocol=http;branch=master"
SRC_URI = "${BASE_URI}"

SRC_URI += "file://0005-Naive-gamepad-support.patch"
SRC_URI += "file://83.patch"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

WPE_BACKEND ?= "essos"

PACKAGECONFIG ?= "${WPE_BACKEND}"
PACKAGECONFIG_append += " gamepad"

PACKAGECONFIG[westeros] = "-DUSE_BACKEND_WESTEROS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland westeros libxkbcommon"
PACKAGECONFIG[essos] = "-DUSE_BACKEND_ESSOS=ON -DUSE_INPUT_LIBINPUT=OFF,-DUSE_BACKEND_ESSOS=OFF,essos libxkbcommon"
PACKAGECONFIG[gamepad] = "-DUSE_GENERIC_GAMEPAD=ON,-DUSE_GENERIC_GAMEPAD=OFF,"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=Release \
"

PROVIDES = "wpe-backend-rdk"
RPROVIDES_${PN} = "wpe-backend-rdk"

PACKAGES =+ "${PN}-platform-plugin"
FILES_${PN}-platform-plugin += "${libdir}/*.so ${bindir}/*"
INSANE_SKIP_${PN}-platform-plugin = "dev-so"

#We need the default package, even if empty for SDK
ALLOW_EMPTY_${PN}="1"
