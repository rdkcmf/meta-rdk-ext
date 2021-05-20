DESCRIPTION = "Google's Brotli compression format"
HOMEPAGE = "https://github.com/google/brotli"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://common/types.h;endline=5;md5=a25dafd481791162df8d611a2b7a9c38"

SRC_URI = "git://github.com/google/brotli \
           file://python.patch \
           file://setup-script.patch"

SRCREV = "2e0d3214c2b6248a486822d2c5267d1c961a29d0"

S = "${WORKDIR}/git"

inherit setuptools3

do_install_append() {
    install -d ${D}${libdir} ${D}${includedir}/brotli/dec ${D}${includedir}/brotli/enc ${D}${includedir}/brotli/common
    install -m 0755 ${WORKDIR}/build/lib.linux-x86_64-3.8/brotli.cpython-38*.so ${D}${libdir}/libbrotli.so
    install -m 0644 ${S}/dec/*.h ${D}${includedir}/brotli/dec/
    install -m 0644 ${S}/enc/*.h ${D}${includedir}/brotli/enc/
    install -m 0644 ${S}/common/*.h ${D}${includedir}/brotli/common/
}

# The libbrotli.so shared lib isn't versioned, so force the .so file into the
# run-time package (and keep it out of the -dev package).

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

BBCLASSEXTEND = "native"
