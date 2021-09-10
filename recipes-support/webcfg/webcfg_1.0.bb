SUMMARY = "webconfig client library"
HOMEPAGE = "https://github.com/xmidt-org/webcfg"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS = "cjson trower-base64 msgpack-c cimplog wdmp-c curl wrp-c"
DEPENDS_append = "${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", " rbus cpeabs", " ", d)}"
DEPENDS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'aker', ' nanomsg libparodus ', '', d)}"

SRCREV =  "9767e3dca4c74a9b4fd40427019b8bd89aa4795b"
SRC_URI = "git://github.com/xmidt-org/webcfg.git"

RDEPENDS_${PN} += "util-linux-uuidgen"

PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

ASNEEDED = ""

inherit pkgconfig cmake pythonnative

EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

EXTRA_OECMAKE += " ${@bb.utils.contains('DISTRO_FEATURES', 'webconfig_bin', '-DWEBCONFIG_BIN_SUPPORT=true', '', d)}"

EXTRA_OECMAKE += " ${@bb.utils.contains('DISTRO_FEATURES', 'aker', '-DFEATURE_SUPPORT_AKER=true', '', d)}"

LDFLAGS += "-lcjson -lmsgpackc -ltrower-base64 -lwdmp-c -lcimplog -lcurl -lwrp-c"

LDFLAGS_append = "${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", " -lrbus -lcpeabs ", " ", d)}"

LDFLAGS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'aker', ' -llibparodus -lnanomsg ', '', d)}"

CFLAGS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'multipartUtility', '-DMULTIPART_UTILITY', '', d)}"

CFLAGS_append = " ${@bb.utils.contains("DISTRO_FEATURES", "WanFailOverSupportEnable", " -DWAN_FAILOVER_SUPPORTED ", " ", d)} "

CFLAGS_append = " \
        -DBUILD_YOCTO \
        -I${STAGING_INCDIR}/wdmp-c \
        -I${STAGING_INCDIR}/cimplog \
        -I${STAGING_INCDIR}/trower-base64 \
        -I${STAGING_INCDIR}/wrp-c \
        -fPIC \
        "
CFLAGS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'webconfig_bin', '-I${STAGING_INCDIR}/rbus -I${STAGING_INCDIR}/rtmessage', '', d)}"

CFLAGS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'aker', '-I${STAGING_INCDIR}/nanomsg -I${STAGING_INCDIR}/libparodus', '', d)}"

CFLAGS_append_dunfell = " -Wno-format-truncation -Wno-sizeof-pointer-memaccess"

SRC_URI_append += " ${@bb.utils.contains('DISTRO_FEATURES', 'webconfig_bin', 'file://webconfig_metadata.json', '', d)}"
SRC_URI_append += " ${@bb.utils.contains('DISTRO_FEATURES', 'webconfig_bin', 'file://webconfig_video_metadata.json', '', d)}"
SRC_URI_append += " ${@bb.utils.contains('DISTRO_FEATURES', 'webconfig_bin', 'file://metadata_parser.py', '', d)}"
SRC_URI_append += " ${@bb.utils.contains('DISTRO_FEATURES', 'webconfig_bin', 'file://webconfig.service', '', d)}"
SRC_URI_append += " ${@bb.utils.contains('DISTRO_FEATURES', 'webconfig_bin', 'file://partners_defaults_webcfg_video.json', '', d)}"

do_install_append_broadband() {
    if ${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", "true", "false", d)}
    then
      install -d ${D}/usr/ccsp/webconfig
      install -d ${D}/etc
      touch ${D}/etc/WEBCONFIG_ENABLE
      (python ${WORKDIR}/metadata_parser.py ${WORKDIR}/webconfig_metadata.json ${D}/etc/webconfig.properties ${MACHINE})
    fi

    if ${@bb.utils.contains("DISTRO_FEATURES", "WanFailOverSupportEnable", "true", "false", d)}
    then
      touch ${D}/etc/CURRENT_INTERFACE
    fi
}

# The libwebcfg.so shared lib isn't versioned, so force the .so file into the
# run-time package (and keep it out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += " ${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", "${exec_prefix}/ccsp/webconfig ${bindir}/*", "${libdir}/*.so", d)}"

ASNEEDED_hybrid = ""
ASNEEDED_client = ""

