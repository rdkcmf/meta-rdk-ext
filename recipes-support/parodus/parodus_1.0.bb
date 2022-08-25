SUMMARY = "parodus client library"
SECTION = "libs"
DESCRIPTION = "C client library for parodus"
HOMEPAGE = "https://github.com/Comcast/parodus"

DEPENDS = "cjson nopoll wrp-c wdmp-c trower-base64 nanomsg msgpack-c rdk-logger log4c util-linux cjwt ucresolv curl"
DEPENDS_append = "${@bb.utils.contains("DISTRO_FEATURES", "seshat", " libseshat ", " ", d)}"
DEPENDS_append = "${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", " rbus rbus-core ", " ", d)}"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRCREV="20ccd81086e6037be7f9d3b8b618c663b19647c4"
SRC_URI = "git://github.com/xmidt-org/parodus.git"

RDEPENDS_${PN} += "util-linux-uuidgen"

RDEPENDS_${PN}_append_dunfell = " bash"

PV = "git+${SRCPV}"
S = "${WORKDIR}/git"

ASNEEDED = ""
LDFLAGS += "-lm -llog4c -lrdkloggers -lcjson -lnopoll -lwrp-c -lwdmp-c -lmsgpackc -ltrower-base64 -luuid -lnanomsg -lcjwt -lucresolv -lresolv"
LDFLAGS_append = "${@bb.utils.contains("DISTRO_FEATURES", "seshat", " -llibseshat ", " ", d)}"
LDFLAGS_append = "${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", " -lrbus -lrbus-core -lrtMessage ", " ", d)}"

CFLAGS_append = " \
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/cjson \
    -I${STAGING_INCDIR}/nopoll \
    -I${STAGING_INCDIR}/wdmp-c \
    -I${STAGING_INCDIR}/wrp-c \
    -I${STAGING_INCDIR}/cimplog \
    -I${STAGING_INCDIR}/nanomsg \
    -I${STAGING_INCDIR}/trower-base64 \
    -I${STAGING_INCDIR}/cjwt \
    -I${STAGING_INCDIR}/ucresolv \
    -DFEATURE_DNS_QUERY \
    "
CFLAGS_append = "${@bb.utils.contains("DISTRO_FEATURES", "seshat", "-I${STAGING_INCDIR}/libseshat ", " ", d)}"
CFLAGS_append = "${@bb.utils.contains("DISTRO_FEATURES", "seshat", "-DENABLE_SESHAT ", " ", d)}"
CFLAGS_append = "${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", " -I${STAGING_INCDIR}/rbus -I${STAGING_INCDIR}/rbus-core -I${STAGING_INCDIR}/rtmessage ", " ", d)}"

inherit pkgconfig cmake
EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true -DFEATURE_DNS_QUERY=true"
EXTRA_OECMAKE_append = "${@bb.utils.contains("DISTRO_FEATURES", "seshat", " -DENABLE_SESHAT=true", " ", d)}"
EXTRA_OECMAKE_append = "${@bb.utils.contains("DISTRO_FEATURES", "webconfig_bin", " -DENABLE_WEBCFGBIN=true", " ", d)}"
EXTRA_OECMAKE_append = "${@bb.utils.contains("DISTRO_FEATURES", "WanFailOverSupportEnable", " -DWAN_FAILOVER_SUPPORTED=true", " ", d)}"

do_compile_prepend_dunfell() {
	sed -i 's/-Werror -Wall/-Wno-error=all/g' ${S}/CMakeLists.txt
}

do_install_append() {
	install -d ${D}${sysconfdir}/ssl/certs
	install -m 0644 ${S}/tests/webpa-rs256.pem ${D}${sysconfdir}/ssl/certs
}

FILES_${PN} += " \
        ${bindir}/* \
	${sysconfdir}/ssl/certs/* \
"
