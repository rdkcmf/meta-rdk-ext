SUMMARY = "TinyXML-2 is a simple, small, efficient, C++ XML parser that can be easily integrating into other programs."
HOMEPAGE = "http://www.grinninglizard.com/tinyxml2/"
LICENSE = "Zlib & Libpng"
LIC_FILES_CHKSUM = "file://readme.md;md5=0033b2f8a25283414b21354670bb1334"
SECTION = "libs"

SRCREV = "1977a7258cc66fd4da7f1e9da05a4933646a7803"

XML2VER = "3.0.0"

PV = "${XML2VER}+git${SRCPV}"

SRC_URI = "git://github.com/leethomason/tinyxml2.git \
           file://libtinyxml2.pc \
	   file://libtinyxml2_change.patch"

S = "${WORKDIR}/git"

EXTRA_CXXFLAGS = "-I. -fPIC"

do_compile() {
	${CXX} ${CXXFLAGS} ${EXTRA_CXXFLAGS} -c -o tinyxml2.o tinyxml2.cpp
	${CXX} ${CXXFLAGS} \
            -shared \
            -Wl,-soname,libtinyxml2.so.${XML2VER} \
            -o libtinyxml2.so.${XML2VER} \
            ${LDFLAGS} \
            tinyxml2.o

}

do_install() {
	install -d ${D}${libdir}
	install -m 0644 ${S}/libtinyxml2.so.${XML2VER} ${D}${libdir}
	ln -sf libtinyxml2.so.${XML2VER} ${D}${libdir}/libtinyxml2.so
	ln -sf libtinyxml2.so.${XML2VER} ${D}${libdir}/libtinyxml2.so.2

	install -d ${D}${includedir}
	install -m 0644 ${S}/tinyxml2.h ${D}${includedir}
	install -Dm644 ${WORKDIR}/libtinyxml2.pc ${D}${libdir}/pkgconfig/libtinyxml2.pc
}

BBCLASSEXTEND += "native"
