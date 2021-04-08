SUMMARY = "webconfig client library"
HOMEPAGE = "https://github.com/xmidt-org/webcfg"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS = "cjson trower-base64 msgpack-c cimplog wdmp-c curl nanomsg wrp-c libparodus"

SRCREV = "120b5ad8759d3d708bdcf94d5dcee91df0dc1cb5"
SRC_URI = "git://github.com/xmidt-org/webcfg.git"

RDEPENDS_${PN} += "util-linux-uuidgen"

PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

ASNEEDED = ""

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

LDFLAGS += "-lcjson -lcimplog -lmsgpackc -ltrower-base64 -lwdmp-c -lcimplog -lcurl -lwrp-c -llibparodus -lnanomsg"

CFLAGS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'multipartUtility', '-DMULTIPART_UTILITY', '', d)}"

CFLAGS_append = " \
        -DBUILD_YOCTO \
        -I${STAGING_INCDIR}/wdmp-c \
        -I${STAGING_INCDIR}/cimplog \
        -I${STAGING_INCDIR}/trower-base64 \
	-I${STAGING_INCDIR}/wrp-c \
	-I${STAGING_INCDIR}/nanomsg \
	-I${STAGING_INCDIR}/libparodus \
        -fPIC \
        "
CFLAGS_append_dunfell = " -Wno-format-truncation -Wno-sizeof-pointer-memaccess"

# The libwebcfg.so shared lib isn't versioned, so force the .so file into the
# run-time package (and keep it out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

ASNEEDED_hybrid = ""
ASNEEDED_client = ""

