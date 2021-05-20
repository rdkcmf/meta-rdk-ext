SUMMARY = "portable Universal Plug and Play SDK"
HOMEPAGE = "http://pupnp.sourceforge.net/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRC_URI = "${SOURCEFORGE_MIRROR}/pupnp/${P}.tar.bz2 \
           file://out-of-tree.patch \
           file://out-of-tree_2.patch \
          "

SRC_URI[md5sum] = "ee16e5d33a3ea7506f38d71facc057dd"
SRC_URI[sha256sum] = "b3142b39601243b50532eec90f4a27dba85eb86f58d4b849ac94edeb29d9b22a"



inherit autotools pkgconfig
