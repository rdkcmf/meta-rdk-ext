SUMMARY = "C++ framework for json-rpc 1.0 and 2.0"
DESCRIPTION = "JsonRpc-Cpp is an OpenSource implementation of JSON-RPC \
               protocol in C++. JSON-RPC is a lightweight remote procedure \
               call protocol similar to XML-RPC."
HOMEPAGE = "https://github.com/cinemast/libjson-rpc-cpp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ee72d601854d5d2a065cf642883c489b"

PV = "0.7.0+git${SRCPV}"

SRC_URI = "git://github.com/cinemast/libjson-rpc-cpp \
           file://0001-cmake-replace-hardcoded-lib-CMAKE_LIBRARY_PATH-with-.patch \
           file://0001-filedescriptorclient-Typecast-min-arguments-correctl.patch \
           file://0001-filedescriptorserver-Include-sys-select.h-before-oth.patch \
           file://0001-memset-and-family-needs-to-include-string.h.patch \
           file://0002-Fix-build-problem-on-Mac.patch \
	   file://0001-jsonrpc-Add-MHD-patch.patch \
           "
SRCREV = "ccbdb41388bdd929828941652da816bf52a0580e"

SECTION = "libs"

DEPENDS = "curl jsoncpp libmicrohttpd"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += "-DCOMPILE_TESTS=NO -DCOMPILE_STUBGEN=NO -DCOMPILE_EXAMPLES=NO \
                  -DBUILD_SHARED_LIBS=YES -DBUILD_STATIC_LIBS=YES \
                  -DCMAKE_LIBRARY_PATH=${libdir} -DTCP_SOCKET_SERVER=YES -DTCP_SOCKET_CLIENT=YES \
"
do_install_append() {
          install -d ${D}${includedir}/jsonrpccpp
          install -d ${D}${includedir}/jsonrpccpp/server
          install -d ${D}${includedir}/jsonrpccpp/server/connectors
          install -d ${D}${includedir}/jsonrpccpp/client
          install -d ${D}${includedir}/jsonrpccpp/client/connectors
          install -m 0644 ${S}/src/jsonrpccpp/*.h ${D}${includedir}/jsonrpccpp
          install -m 0644 ${S}/src/jsonrpccpp/server/*.h ${D}${includedir}/jsonrpccpp/server
          install -m 0644 ${S}/src/jsonrpccpp/server/connectors/*.h ${D}${includedir}/jsonrpccpp/server/connectors
          install -m 0644 ${S}/src/jsonrpccpp/client/*.h ${D}${includedir}/jsonrpccpp/client
          install -m 0644 ${S}/src/jsonrpccpp/client/connectors/*.h ${D}${includedir}/jsonrpccpp/client/connectors
}

