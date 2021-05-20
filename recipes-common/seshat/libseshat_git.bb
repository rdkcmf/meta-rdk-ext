SUMMARY = "Nanomsg and wrp service discovery agent"
HOMEPAGE = "https://github.com/Comcast/seshat"
SECTION = "console/utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS = "cmake-native cjson cimplog wrp-c trower-base64 nanomsg msgpack-c rdk-logger log4c util-linux"

SRCREV = "470f8d5e9457755028aae6da65d3df9b62a53942"
SRC_URI = "git://github.com/Comcast/seshat.git"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

inherit pkgconfig

CFLAGS_append = " \
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/cjson \
    -I${STAGING_INCDIR}/wrp-c \
    -I${STAGING_INCDIR}/cimplog \
    -I${STAGING_INCDIR}/nanomsg \
    -I${STAGING_INCDIR}/trower-base64 \
    "

do_compile() {
    rm -rf ${S}/build
    mkdir ${S}/build
    cd ${S}/build
    cmake -DCMAKE_SKIP_BUILD_RPATH=true -DBUILD_TESTING=OFF -DBUILD_YOCTO=true ..
    make libseshat.shared
}

do_install() {
    install -d ${D}${libdir}
    install -m 0644 ${S}/build/libseshat/src/liblibseshat.so* ${D}${libdir}/

    install -d ${D}${includedir}/libseshat
    install -m 0644 ${S}/libseshat/src/*.h ${D}${includedir}/libseshat/
}

# The libseshat.so shared lib isn't versioned, so force the .so file into the
# run-time package (and keep it out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
