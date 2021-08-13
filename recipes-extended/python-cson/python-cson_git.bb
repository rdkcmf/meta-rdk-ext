# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>.
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Python library for CSON (schema-compressed JSON)"
HOMEPAGE = "https://github.com/gt3389b/python-cson/"
LICENSE = "MIT"
SECTION = "devel/python"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7709d2635e63ab96973055a23c2a4cac"

SRCREV = "d0c1573ca68d7840099c0649b870fd23c3da4a9d"
SRC_URI = "git://github.com/gt3389b/python-cson.git"

S = "${WORKDIR}/git"

RDEPENDS_${PN}_class-native = ""
DEPENDS_append_class-native = " ${@bb.utils.contains('DISTRO_FEATURES', 'dunfell', 'python3native', 'pythonnative', d)} "

inherit setuptools

BBCLASSEXTEND = "native"

