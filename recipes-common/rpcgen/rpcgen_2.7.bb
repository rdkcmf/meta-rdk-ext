SRC_URI = "${GNU_MIRROR}/glibc/glibc-${PV}.tar.gz \
           file://autotools.patch \
          "

LICENSE = "GPLv2 & LGPLv2.1"

SRC_URI[md5sum] = "0b4bc75fb7413bbd7e43c0a0e7c672b6"
SRC_URI[sha256sum] = "f5ef515cb70f8d4cfcee0b3aac05b73def60d897bdb7a71f4356782febfe415a"

LIC_FILES_CHKSUM = "file://${S}/../LICENSES;md5=07a394b26e0902b9ffdec03765209770 \
      file://${S}/../COPYING;md5=393a5ca445f6965873eca0259a17f833 \
      "

S = "${WORKDIR}/glibc-${PV}/sunrpc"

inherit autotools

CFLAGS = "std=gnu99"


BBCLASSEXTEND = "native"
