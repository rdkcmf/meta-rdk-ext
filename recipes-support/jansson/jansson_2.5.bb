SUMMARY = "Jansson is a C library for encoding, decoding and manipulating JSON data"
HOMEPAGE = "http://www.digip.org/jansson/"
PRIORITY = "optional"
PR = "r1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=74ead53343bf648f8f588341b2f8fa16"
SRC_URI[md5sum] = "46ac93bec48aacf207b67b51c8fbf7f1"
SRC_URI[sha256sum] = "dd8bbfb38ad5031ce88e066b14d3b7e9c7113243daa7f4cde1994b7fc90bb1b1"

SRC_URI = "http://www.digip.org/jansson/releases/${BPN}-${PV}.tar.gz"

inherit autotools pkgconfig

