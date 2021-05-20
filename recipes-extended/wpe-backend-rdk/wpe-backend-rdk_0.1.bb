SUMMARY = "the wpe library"
HOMEPAGE = "http://www.webkitforwayland.org/"
LICENSE = "BSD-2-Clause & MIT"
LIC_FILES_CHKSUM = "file://cmake/FindBCMHost.cmake;beginline=9;endline=28;md5=3f28792817862cbda9e3fe86b3ae87ba"

DEPENDS += "wpe-backend"

PV = "0.1+git${SRCPV}"

SRCREV = "ca1f28469fedb4154c04d4531fbd30beae968301"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEBackend-rdk.git;protocol=http;branch=master"
SRC_URI = "${BASE_URI}"

SRC_URI += "file://0000-play-pause-mapping.patch"
SRC_URI += "file://0001-Fix-key-repeat-and-destruction-of-EGLTarget.patch"
SRC_URI += "file://0002-XRE-key-mapping.patch"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

WPE_BACKEND ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', '', d)}"

PACKAGECONFIG ?= "${WPE_BACKEND}"

PACKAGECONFIG[wayland] = "-DUSE_BACKEND_WAYLAND=ON -DUSE_WPE_BUFFER_MANAGEMENT_BCM_RPI=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland libxkbcommon"
PACKAGECONFIG[westeros] = "-DUSE_BACKEND_WESTEROS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_HOLE_PUNCH_GSTREAMER=ON -DUSE_HOLE_PUNCH_EXTERNAL=ON -DUSE_WESTEROS_SINK=ON,,wayland westeros libxkbcommon"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=Release \
"

PROVIDES = "wpe-backend-rdk"
RPROVIDES_${PN} = "wpe-backend-rdk"

PACKAGES =+ "${PN}-platform-plugin"
FILES_${PN}-platform-plugin += "${libdir}/*.so"
INSANE_SKIP_${PN}-platform-plugin = "dev-so"


