SUMMARY = "This receipe compiles and builds aker."
SECTION = "libs"
DESCRIPTION = "Module for selectively blocking MAC addresses based on a succinct schedule in MsgPack"
HOMEPAGE = "https://github.com/Comcast/aker"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRCREV = "08cfc84ae7688f0df5e609db983ce77de24b8c57"
SRC_URI = "git://github.com/xmidt-org/aker.git;branch=main"
SRC_URI += "file://aker-01.patch"

PV = "git+${SRCPV}"
S = "${WORKDIR}/git"

ASNEEDED = ""

DEPENDS = "libparodus wrp-c trower-base64 msgpack-c rdk-logger log4c util-linux"

CFLAGS_append = " \
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/libparodus \
    -I${STAGING_INCDIR}/msgpack \
    -I${STAGING_INCDIR}/wrp-c \
    -I${STAGING_INCDIR}/cimplog \
    -I${STAGING_INCDIR}/trower-base64 \
    "

inherit pkgconfig coverity cmake
EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

do_install_append() {
   if ${@bb.utils.contains('DISTRO_FEATURES', 'aker', 'true', 'false', d)}; then
        install -d ${D}/etc
        cd ${D}/etc
        touch AKER_ENABLE
        cd -
   fi
}

FILES_${PN} += " \
        ${bindir}/* \
"
FILES_${PN} += " \
        ${@bb.utils.contains("DISTRO_FEATURES", "aker", "/etc/* ", " ", d)} \
"
