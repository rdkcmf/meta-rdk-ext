SUMMARY = "Duktape is an embeddable Javascript engine, with a focus on portability and compact footprint."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SECTION = "libs"

SRC_URI = "https://github.com/svaarala/duktape-releases/raw/master/${PN}-${PV}.tar.xz"
SRC_URI[md5sum] = "0f7c9fac5547f7f3fc1c671fc90b2ccf"
SRC_URI[sha256sum] = "62f72206427633077cb02e7ccd2599ace4d254db409334593b86d262c0d50c14"

SRC_URI += "file://CMakeLists.txt"

inherit pkgconfig cmake

do_configure_prepend () {
    cp ${WORKDIR}/CMakeLists.txt ${WORKDIR}/${PN}-${PV}/
}
