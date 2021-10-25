DEPENDS = "elfutils"
LICENSE = "LGPLv2.1 & GPLv2 & BSD-2"
LIC_FILES_CHKSUM = "file://COPYING;md5=e54ca27c705f651cc9a129197bf989a3"

SRC_URI = "http://www.prevanders.net/libdwarf-${PV}.tar.gz"
#SRC_URI = "file://libdwarf-20210528.tar.gz"

SRC_URI[md5sum] = "61fc47c4fab6730e0262c75a8432c17f"
SRC_URI[sha256sum] = "b8ba0ee9b70d2052d45272489d79bf456c4d342fc8c3bba45038afc50ec6e28b"

S = "${WORKDIR}/libdwarf-${PV}"

inherit autotools
