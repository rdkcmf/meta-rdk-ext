DESCRIPTION = "Utilities for generating documentation from source code"
HOMEPAGE = "http://www.doxygen.org/"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b380c86cea229fa42b9e543fc491f5eb"

DEPENDS = "flex-native bison-native"

SRC_URI = "http://ftp.stack.nl/pub/users/dimitri/${BP}.src.tar.gz"
SRC_URI[md5sum] = "084f1db244b77b3abff335550ef8eec5"
SRC_URI[sha256sum] = "0e60e794fb172d3fa4a9a9535f0b8e0eeb04e8366153f6b417569af0bcd61fcd"

EXTRA_OECONF = "--prefix ${prefix}"

do_configure () {
	./configure ${EXTRA_OECONF}

	# TODO on rebuilds will repeatedly append.  Change logic to include a
	# separate file and overwrite that file?
        echo "TMAKE_CC=${CC}" >> tmake/lib/linux-g++/tmake.conf
        echo "TMAKE_CXX=${CXX}" >> tmake/lib/linux-g++/tmake.conf
        echo "TMAKE_CFLAGS=${CFLAGS}" >> tmake/lib/linux-g++/tmake.conf
        echo "TMAKE_CXXFLAGS=${CXXFLAGS}" >> tmake/lib/linux-g++/tmake.conf
        echo "TMAKE_LINK=${CXX}" >> tmake/lib/linux-g++/tmake.conf
        echo "TMAKE_LFLAGS=${LDFLAGS}" >> tmake/lib/linux-g++/tmake.conf
}

do_install() {
	oe_runmake install DESTDIR=${D} MAN1DIR=share/man/man1
}

BBCLASSEXTEND = "native"
