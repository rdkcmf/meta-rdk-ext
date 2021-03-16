DESCRIPTION = "Font rendering capabilities for complex non-Roman writing systems"
HOMEPAGE = "https://github.com/silnrsi/graphite/"
LICENSE = "LGPLv2.1|GPLv2+|MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d5025d4aa3495befef8f17206a5b0a1"
SECTION = "devel"
DEPENDS = "freetype"

SRC_URI = "https://github.com/silnrsi/graphite/releases/download/${PV}/graphite-${PV}.tgz"
SRC_URI[md5sum] = "12eb607e0f458febe348ae69b832b300"
SRC_URI[sha256sum] = "475e7657ac606ed8805518031729c1273cf7d9d422169ac6f7882e01d832af75"

inherit gettext cmake

FILES_${PN}-dev += " ${datadir}/graphite2/graphite2-release.cmake  ${datadir}/graphite2/graphite2.cmake"
