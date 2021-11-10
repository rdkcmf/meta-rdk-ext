SUMMARY = "C library for Web Routing Protocol (WRP)"
HOMEPAGE = "https://github.com/Comcast/wrp-c"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS = "trower-base64 msgpack-c cimplog"

SRCREV = "38d26e8c83aae27b2b83130b68e15b919e4b5e37"
SRC_URI = "git://github.com/Comcast/wrp-c.git"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

ASNEEDED = ""

inherit pkgconfig cmake

EXTRA_OECMAKE = "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

LDFLAGS += "-lcimplog -lmsgpackc -ltrower-base64"

# The libwrp-c.so shared lib isn't versioned, so force the .so file into the
# run-time package (and keep it out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

ASNEEDED_hybrid = ""
ASNEEDED_client = ""
