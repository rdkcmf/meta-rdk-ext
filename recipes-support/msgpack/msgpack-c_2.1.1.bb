SUMMARY = "MessagePack implementation for C and C++"
DESCRIPTION = "MessagePack is an efficient binary serialization format. It's like JSON. but fast and small"
HOMEPAGE = "http://msgpack.org/index.html"
LICENSE = "BSL-1.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=7a858c074723608e08614061dc044352 \
                    file://COPYING;md5=0639c4209b6f2abf1437c813b208f2d3 \
                    file://LICENSE_1_0.txt;md5=e4224ccaecb14d942c71d31bef20d78c \
                   "

PV .= "+git${SRCPV}"

SRCREV = "20ef1f925b007f170ab1c257e4aa61fdd0927773"

SRC_URI = "git://github.com/msgpack/msgpack-c \
           file://0001-Comment-intentional-fallthrough-in-case-statements.patch \
           "
SRC_URI_append_dunfell = " file://0001-Fix-Werror-class-memaccess.patch \
                         "
inherit cmake pkgconfig

EXTRA_OECMAKE += " -DMSGPACK_BUILD_TESTS=OFF "

S = "${WORKDIR}/git"

FILES_${PN}-dev += "${libdir}/cmake/msgpack/*.cmake"

# ----------------------------------------------------------------------------
# Temp solution to support renaming of recipe from msgpackc -> msgpack-c
# Can be removed when all recipes which currently depend on msgpackc have
# been updated to depend on msgpack-c instead.
# ----------------------------------------------------------------------------

PROVIDES = "msgpackc"
RPROVIDES_${PN} += "msgpackc"

# ----------------------------------------------------------------------------
