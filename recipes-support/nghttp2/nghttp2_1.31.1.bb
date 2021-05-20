DESCRIPTION = "Recipe for component nghttp an HTTP/2 client"
HOMEPAGE = "https://nghttp2.org/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=764abdf30b2eadd37ce47dcbce0ea1ec"

DEPENDS = "cunit openssl libev zlib c-ares libxml2 systemd jansson"

SRC_URI = " https://github.com/nghttp2/nghttp2/releases/download/v${PV}/nghttp2-${PV}.tar.gz \
            file://cares-nghttp-configure.patch \
          "
SRC_URI[md5sum] = "b68392c65f25241cc4aec9b26f51c0a7"
SRC_URI[sha256sum] = "66562e26c2a8112eb3d15e8521290352cbb5a8295271583840cb9a9f225c7195"

inherit autotools pkgconfig python3native

LDFLAGS += "-lcares"

EXTRA_OECONF = "--enable-app --disable-python-bindings"

PACKAGES =+ "${PN}-server ${PN}-client ${PN}-proxy ${PN}-common"

FILES_${PN}-server = "${bindir}/nghttpd"
FILES_${PN}-client = "${bindir}/nghttp"
FILES_${PN}-proxy  = "${bindir}/nghttpx"
FILES_${PN}-common = "${bindir}/h2load ${libdir}/libnghttp2.so.14.16.1 ${datadir}/nghttp2/fetch-ocsp-response"
