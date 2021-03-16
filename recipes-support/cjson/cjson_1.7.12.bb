SUMMARY = "Ultralightweight JSON parser in ANSI C"
HOMEPAGE = "https://github.com/DaveGamble/cJSON"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "https://github.com/DaveGamble/cJSON/archive/v${PV}.tar.gz"

SRC_URI[md5sum] = "249d4aa23da06df19fb5a8e66c285363"
SRC_URI[sha256sum] = "760687665ab41a5cff9c40b1053c19572bcdaadef1194e5cba1b5e6f824686e7"

inherit cmake pkgconfig

LDFLAGS += "-lm"

do_unpack_extra() {
    mkdir -p ${S}
    cp -r ${S}/../cJSON-1.7.12/* ${S}/.
    rm -rf ${S}/../cJSON-1.7.12/
}
addtask unpack_extra after do_unpack before do_patch

do_install_append() {
	# Create symlink to support meta-rdk components which expect cJSON.h to
	# be found in the toplevel sysroot ${includedir} rather than within the
	# cjson subdirectory. Fixme: The real solution would be to fix those
	# recipes and then remove this symlink.
	ln -s cjson/cJSON.h ${D}${includedir}/cJSON.h
}

FILES_${PN}-dev += "${libdir}/cmake"

