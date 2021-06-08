SUMMARY = "Fast and portable XML parser and Jabber protocol library"
HOMEPAGE = "https://github.com/meduketto/iksemel"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=d8045f3b8f929c1cb29a1e3fd737b499"

DEPENDS += " mbedtls"

SRCREV = "978b733462e41efd5db72bc9974cb3b0d1d5f6fa"
PV = "1.5+git${SRCPV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/iksemel:"

SRC_URI = "git://github.com/meduketto/iksemel.git;protocol=https"

S = "${WORKDIR}/git"

inherit autotools pkgconfig lib_package

CFLAGS_append = " -DTLS_NO_VFY"

# TLS support provided by mbedtls (see patch file)
EXTRA_OECONF = "--disable-python --without-gnutls --without-openssl"
