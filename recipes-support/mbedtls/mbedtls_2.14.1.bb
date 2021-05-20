SUMMARY = "mbed TLS offers an SSL library with an intuitive API and readable source code, so you can actually understand what the code does."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=302d50a6369f5f22efdb674db908167a"

SECTION = "libs"

SRC_URI = "git://github.com/ARMmbed/mbedtls.git;protocol=https;branch=mbedtls-2.14"
SRCREV = "60fbd5bdf05c223b641677204469b53c2ff39d4e"

SRC_URI += "file://0001-Ciphersuites-Disable-64-bit-block-and-nonstandard-ci.patch"
SRC_URI += "file://0002-fix-x86-pic-asm-with-old-gcc.patch"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

do_configure_append() {
    ${S}/scripts/config.pl set MBEDTLS_THREADING_PTHREAD
    ${S}/scripts/config.pl set MBEDTLS_THREADING_C
}

EXTRA_OECMAKE = "-DENABLE_TESTING=OFF -DUSE_SHARED_MBEDTLS_LIBRARY=On"
