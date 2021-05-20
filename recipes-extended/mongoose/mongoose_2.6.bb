SUMMARY = "easy to use web server"
DESCRIPTION = "Simple, functional, embeddable web server to make it \
easy for application and device developers to implement web interface \
for their application and devices, and to offer a simple web \
development environment."

HOMEPAGE = "http://code.google.com/p/mongoose/"
SECTION = "console"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c919934a724781b6cd4a296b0fc27dc2"
LIC_FILES_CHKSUM = "file://mongoose.c;beginline=1;endline=23;md5=660cfd3ad92e2cc9e655a4567a738309"

S = "${WORKDIR}/${BPN}/"
SRC_URI = "http://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/mongoose/mongoose-${PV}.tgz"

SRC_URI += " \
	file://mongoose_generic_v2.6.patch \
	file://dont_strip.patch \
	file://mongoose-reorder-buildargs.patch \
	file://Add-options-support.patch \
	"

SRC_URI[md5sum] = "56afae4cfb86e836024df31224db8104"
SRC_URI[sha256sum] = "d87c9c50404ae8a88bddf1cedf2596b0887fc226d4c62e46862fa99c270a8e8d"

EXTRA_OEMAKE = "'CC=${CC}' 'COPT=${CFLAGS}'"

TARGET_CC_ARCH +="${LDFLAGS}"

do_compile () {
    oe_runmake linux
}

# Shared libs created by the RDK build aren't versioned, so we need
# to force the .so files into the runtime package (and keep them
# out of -dev package).
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

do_install () {
    install -d ${D}${bindir} ${D}${includedir}
    install -m 0755 mongoose ${D}${bindir}
    install -d ${D}${libdir}
    install -m 0755 _mongoose.so ${D}${libdir}/libmongoose.so
    install -m 0755 *.h ${D}${includedir}/
}
