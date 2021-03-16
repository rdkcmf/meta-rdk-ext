DESCRIPTION = "Recipe to build google benchmark 1.3.0"
HOMEPAGE = "https://github.com/google/benchmark"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../benchmark-1.3.0/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57" 

SRC_URI = "https://github.com/google/benchmark/archive/v1.3.0.zip"
SRC_URI[md5sum] = "c554f04e914a531d9ad267e7c96d994a"
SRC_URI[sha256sum] = "51c2d2d35491aea83aa6121afc4a1fd9262fbd5ad679eb5e03c9fa481e42571e"

inherit cmake pkgconfig
BBCLASSEXTEND = "native nativesdk"
S = "${WORKDIR}/${BPN}-${PV}"

FILES_${PN} += "${libdir}/*"
