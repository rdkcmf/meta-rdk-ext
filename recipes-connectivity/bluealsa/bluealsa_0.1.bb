SUMMARY = "Bluetooth Audio ALSA Backend"
HOMEPAGE = "https://github.com/Arkq/bluez-alsa"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=72d868d66bdd5bf51fe67734431de057"

SRC_URI = "git://github.com/Arkq/bluez-alsa.git;protocol=https;branch=master"
SRCREV  = "${AUTOREV}"

S  = "${WORKDIR}/git"

DEPENDS += "alsa-lib bluez5 systemd glib-2.0 sbc"

PACKAGECONFIG[aac]  = "--enable-aac, --disable-aac, "
PACKAGECONFIG[aptx] = "--enable-aptx,--disable-aptx,"
PACKAGECONFIG[ofono] = "--enable-ofono, --disable-ofono,"

inherit autotools pkgconfig

PACKAGECONFIG += "ofono"

# enable debug tools in devel images
PACKAGECONFIG[hcitop] = "--enable-hcitop, --disable-hcitop, libbsd ncurses"
PACKAGECONFIG[rfcomm] = "--enable-rfcomm, --disable-rfcomm,"
PACKAGECONFIG_append_agl-devel = " hcitop rfcomm"
PACKAGECONFIG[debug] = "--enable-debug, --disable-debug,"


do_install_append () {
    install -m 0755 ${WORKDIR}/build/src/bluealsa ${D}${bindir}/
}


FILES_${PN} += "${libdir}/*"

