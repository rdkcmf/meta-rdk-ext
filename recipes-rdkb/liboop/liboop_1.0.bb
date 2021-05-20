DESCRIPTION = "Liboop is a low-level event loop management library for POSIX-based operating systems"
HOMEPAGE = "http://liboop.org/"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=8b54f38ccbd44feb80ab90e01af8b700"

SRC_URI = "http://ftp.debian.org/debian/pool/main/libo/liboop/liboop_${PV}.orig.tar.gz"

SRC_URI[md5sum] = "36cb971047d3af02369446f5e0b315a2"
SRC_URI[sha256sum] = "34d83c6e0f09ee15cb2bc3131e219747c3b612bb57cf7d25318ab90da9a2d97c"


DEPENDS += "readline glib-2.0"

inherit autotools pkgconfig

S = "${WORKDIR}/${PN}-${PV}"




