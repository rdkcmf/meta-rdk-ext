LICENSE = "LGPLv2.1 & LWS-exceptions"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f197d69f6bda1c450e2173a161286269"

SRC_URI = "git://github.com/warmcat/libwebsockets.git;protocol=https;branch=v2.0-stable;tag=v2.0.2"

S = "${WORKDIR}/git"

DEPENDS = "zlib"

EXTRA_OECMAKE = "-DLWS_WITH_SSL=OFF -DLWS_WITHOUT_TESTAPPS=ON -DLWS_WITH_STATIC=OFF"

inherit cmake pkgconfig

# TODO: change so that the unnecessary files are not installed at all
# rather than being installed first and removed later.
do_install_append() {
	rm -rf ${D}${libdir}/cmake/libwebsockets
	rmdir ${D}${libdir}/cmake
}
