SUMMARY = "JSON C++ lib used to read and write json file."
DESCRIPTION = "Jsoncpp is an implementation of a JSON (http://json.org) reader \
               and writer in C++. JSON (JavaScript Object Notation) is a \
               lightweight data-interchange format. It is easy for humans to \
               read and write. It is easy for machines to parse and generate."

HOMEPAGE = "http://sourceforge.net/projects/jsoncpp/"

SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c56ee55c03a55f8105b969d8270632ce"

SRC_URI = "${SOURCEFORGE_MIRROR}/jsoncpp/jsoncpp-src-${PV}.tar.gz \
	file://0001-jsoncpp-rename-features-header-file.patch \
"

S = "${WORKDIR}/jsoncpp-src-${PV}"

SRC_URI[md5sum] = "363e2f4cbd3aeb63bf4e571f377400fb"
SRC_URI[sha256sum] = "d4d193d163f520b08b9155cba978615892ca1359d77e3fb261fce2f86d09b283"

do_compile() {
	${CXX} src/lib_json/*.cpp -Iinclude -shared -fPIC \
		-Wl,-soname,libjsoncpp.so.${PV} -o libjsoncpp.so.${PV} ${CXXFLAGS} ${LDFLAGS}
}

do_install() {
	install -d ${D}${includedir}/json ${D}${libdir}
	install -m 0644 ${S}/include/json/*.h ${D}${includedir}/json
	install -m 0755 ${S}/libjsoncpp.so.${PV} ${D}${libdir}
	ln -s libjsoncpp.so.${PV} ${D}${libdir}/libjsoncpp.so
}
