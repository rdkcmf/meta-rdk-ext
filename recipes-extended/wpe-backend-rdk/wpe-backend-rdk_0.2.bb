SUMMARY = "WPE WebKit RDK backend"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded"
SECTION = "wpe"
LICENSE = "BSD-2-Clause & Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ab5b52d145a58f5fcc0e2a531e7a2370"

DEPENDS += "wpe-backend"

PV = "0.2+git${SRCPV}"

#revision date: Oct 10 2018
SRCREV = "99c0f3fafd6e71552bd42e89540175d646e15b7d"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEBackend-rdk.git;protocol=http;branch=master"
SRC_URI = "${BASE_URI}"

SRC_URI += "file://0001-Fix-key-repeat-and-destruction-of-EGLTarget_0.2.patch"
SRC_URI += "file://0002-XRE-key-mapping_0.2.patch"
SRC_URI += "file://0003-Dispatch-events-in-glib-context-View-was-created.patch"
SRC_URI += "file://0004-Accept-wpe-framework-client-identifier.patch"
SRC_URI += "file://0005-Naive-gamepad-support.patch"
SRC_URI += "file://0007-Scaling-on-missing-repeating-composition-platforms.patch"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

WPE_BACKEND ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', '', d)}"

PACKAGECONFIG ?= "${WPE_BACKEND}"
PACKAGECONFIG_append += "${@bb.utils.contains('DISTRO_FEATURES', 'gaming-support', 'gamepad', '', d)}"

PACKAGECONFIG[wayland] = "-DUSE_BACKEND_WAYLAND=ON -DUSE_WPE_BUFFER_MANAGEMENT_BCM_RPI=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland libxkbcommon"
PACKAGECONFIG[westeros] = "-DUSE_BACKEND_WESTEROS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_HOLE_PUNCH_GSTREAMER=ON -DUSE_HOLE_PUNCH_EXTERNAL=ON -DUSE_WESTEROS_SINK=ON,,wayland westeros libxkbcommon"
PACKAGECONFIG[gamepad] = "-DUSE_GENERIC_GAMEPAD=ON,-DUSE_GENERIC_GAMEPAD=OFF,"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=Release \
"

PROVIDES = "wpe-backend-rdk"
RPROVIDES_${PN} = "wpe-backend-rdk"

PACKAGES =+ "${PN}-platform-plugin"
FILES_${PN}-platform-plugin += "${libdir}/*.so ${bindir}/*"
INSANE_SKIP_${PN}-platform-plugin = "dev-so"
