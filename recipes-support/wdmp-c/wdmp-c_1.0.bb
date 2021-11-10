SUMMARY = "C library for WebPA Data Model Parser (WDMP)"
HOMEPAGE = "https://github.com/Comcast/wdmp-c"
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

DEPENDS = "cjson cimplog"

SRCREV = "e959418e373cceae663d8913cfb60a436d6b2140"
SRC_URI = "git://github.com/xmidt-org/wdmp-c.git"
PV = "git+${SRCPV}"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true"

LDFLAGS += "-lm"

# The libwdmp-c.so shared lib isn't versioned, so force the .so file into the
# run-time package (and keep it out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
