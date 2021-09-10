SUMMARY = "cpeabs library"
HOMEPAGE = "https://github.com/xmidt-org/cpeabs"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSES/Apache-2.0.txt;md5=c846ebb396f8b174b10ded4771514fcc"

DEPENDS = "cjson msgpack-c rbus wdmp-c cimplog"

SRCREV = "cffe7420efdbed9038c5f89a9a1f92be4a9ab8e6"

SRC_URI = "git://github.com/xmidt-org/cpeabs.git"

PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

ASNEEDED = ""

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

LDFLAGS += "-lcjson -lmsgpackc -lwdmp-c -lcimplog -lrbus"

CFLAGS_append = " ${@bb.utils.contains("DISTRO_FEATURES", "WanFailOverSupportEnable", " -DWAN_FAILOVER_SUPPORTED ", " ", d)} "

CFLAGS_append = " \
        -DBUILD_YOCTO \
        -I${STAGING_INCDIR}/cjson \
        -I${STAGING_INCDIR}/rbus \
        -I${STAGING_INCDIR}/rtmessage \
        -I${STAGING_INCDIR}/wdmp-c \ 
        -I${STAGING_INCDIR}/cimplog \
        -fPIC \
        "
CFLAGS_append_dunfell = " -Wno-format-truncation -Wno-sizeof-pointer-memaccess"

# The libcpeabs.so shared lib isn't versioned, so force the .so file into the
# run-time package (and keep it out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

ASNEEDED_hybrid = ""
ASNEEDED_client = ""
