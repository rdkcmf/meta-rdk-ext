#
# Released under the MIT license (see COPYING.MIT for the terms)


SUMMARY = "Library of utility functions from BSD systems"
DESCRIPTION = "This library provides useful functions commonly found on BSD systems, \
               and lacking on others like GNU systems, thus making it easier to port \
               projects with strong BSD origins, without needing to embed the same \
               code over and over again on each project."

HOMEPAGE = "http://libbsd.freedesktop.org/wiki/"
LICENSE = "BSD-2-Clause & BSD-3-Clause & BSD-4-Clause & ISC & MIT & PD & Beerware"
LIC_FILES_CHKSUM = "file://COPYING;md5=145ec05a217d8f879f29cfc5f83084be"
SECTION = "libs"
DEPENDS = ""

SRC_URI = "http://libbsd.freedesktop.org/releases/${BPN}-${PV}.tar.xz"
SRC_REV = "e4475738fefe3b0c7388b293e8b0828560c6b830"
SRC_URI[md5sum] = "cdee252ccff978b50ad2336278c506c9"
SRC_URI[sha256sum] = "b2f644cae94a6e2fe109449c20ad79a0f6ee4faec2205b07eefa0020565e250a"

inherit autotools pkgconfig
