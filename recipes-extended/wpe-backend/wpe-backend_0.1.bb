SUMMARY = "the wpe library"
HOMEPAGE = "http://www.webkitforwayland.org/"
LICENSE = "BSD-2-Clause & MIT"
LIC_FILES_CHKSUM = "file://include/wpe/input.h;beginline=5;endline=24;md5=b1c8cb6b0857048a21b33611f01c575a"

DEPENDS += "libinput"

PV = "0.1+git${SRCPV}"

SRCREV = "baa960b781a88addf778650e1226563ef12f8170"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEBackend.git;protocol=http;branch=master"
SRC_URI = "${BASE_URI}"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

PROVIDES = "wpe-backend"
RPROVIDES_${PN} = "wpe-backend"
RPROVIDES_${PN} += "libwpe-0.2-1"

EXTRA_OECONF += " \
    -DWPE_BACKEND=libWPEBackend.so \
    -DCMAKE_C_FLAGS=-D_GNU_SOURCE \
"

CXXFLAGS += " \
-D_GLIBCXX_USE_CXX11_ABI=0 \
-D_GNU_SOURCE \
"

CFLAGS += " \
-D_GNU_SOURCE \
"
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libWPEBackend.so ${libdir}/pkgconfig/wpe.pc"
INSANE_SKIP_${PN} ="dev-so"

