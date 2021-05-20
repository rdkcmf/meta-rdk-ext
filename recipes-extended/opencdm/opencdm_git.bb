SUMMARY = "Open Content Decryption Module."
HOMEPAGE = "https://www.fokus.fraunhofer.de/en"
LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ea83f8bc099c40bde8c4f2441a6eb40b"

DEPENDS = "glib-2.0"
DEPENDS_append_libc-musl = " libtirpc"
CPPFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
CXXFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
CFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"
LDFLAGS_append_libc-musl = " -ltirpc"

SRCREV = "d94143f8b7a7404c4fd9aa9e1e415b6ca770ee22"
PV = "1.0.gitr${SRCPV}"
S = "${WORKDIR}/git"

PROVIDES = "opencdm"
RPROVIDES_${PN} = "opencdm"

SRC_URI = "git://github.com/WebPlatformForEmbedded/WPEOpenCDM;branch=wpe"
SRC_URI += "file://0001-comcast-fixsemaphorebug-removelogmessages.patch \
            file://0003-comcast-check-for-mediakeysessionupdate-failure.patch \
	    file://0002-comcast-fix-Wno-narrowing-gcc6-error.patch \
"
SRC_URI_append_morty = " file://wpe-test-makefile.patch"

do_install() {

	install -d ${D}${libdir}
	install -m 0755 ${B}/src/browser/wpe/lib/libocdm.so ${D}${libdir}

	install -d ${D}${includedir}/opencdm
    install -m 0644 ${S}/src/browser/wpe/include/*  ${D}${includedir}/opencdm
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"
