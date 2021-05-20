LICENSE = "LGPLv2.1 & LWS-exceptions"
LIC_FILES_CHKSUM = "file://LICENSE;md5=041a1dec49ec8a22e7f101350fd19550"

SRC_URI = "git://github.com/warmcat/libwebsockets.git;protocol=https;branch=v1.5-stable;tag=v1.5-chrome47-firefox41 \
           file://zeroread.patch \
           file://0001-openssl-deprecated-declarations.patch \
           "

FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"

S = "${WORKDIR}/git"

DEPENDS = "openssl zlib"

EXTRA_OECMAKE = "-DLWS_WITH_SSL=ON -DLWS_WITHOUT_TESTAPPS=ON -DLWS_WITH_STATIC=OFF \
-DLWS_INSTALL_INCLUDE_DIR:PATH='${includedir}/${BPN}' \
-DLWS_INSTALL_LIB_DIR:PATH='${libdir}/${BPN}' \
"

inherit cmake pkgconfig

# TODO: change so that the unnecessary files are not installed at all
# rather than being installed first and removed later.
do_install_append() {
       rm -rf ${D}${libdir}/cmake/libwebsockets
       rm -rf ${D}${libdir}/pkgconfig/libwebsockets.pc
}

do_install_append_morty() {
       rm -rf ${STAGING_DIR}/${MACHINE}/pkgdata/runtime-reverse/libwebsockets-dbg
       rm -rf ${STAGING_DIR}/${MACHINE}/pkgdata/runtime-reverse/libwebsockets-dev
}

FILES_${PN}-dev =+ "${libdir}/${BPN}/libwebsockets.so"

SSTATE_DUPWHITELIST += "${STAGING_DIR}/${MACHINE}/pkgdata/runtime-reverse/libwebsockets-dev ${STAGING_DIR}/${MACHINE}/pkgdata/runtime-reverse/libwebsockets-dbg ${STAGING_LIBDIR}/libwebsockets.so ${STAGING_LIBDIR}/pkgconfig/libwebsockets.pc"
