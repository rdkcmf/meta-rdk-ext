SUMMARY = "JSON C++ lib used to read and write json file."
DESCRIPTION = "Jsoncpp is an implementation of a JSON (http://json.org) reader \
               and writer in C++. JSON (JavaScript Object Notation) is a \
               lightweight data-interchange format. It is easy for humans to \
               read and write. It is easy for machines to parse and generate."

HOMEPAGE = "http://sourceforge.net/projects/jsoncpp/"

SECTION = "libs"

LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://LICENSE;md5=fa2a23dd1dc6c139f35105379d76df2b"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=5598b558077db8f7f706e1fd1ac4253b"


SRC_URI = "git://github.com/open-source-parsers/jsoncpp.git \
           file://0001-Build-Issue-Fix-snprintf.patch \
"

# release 1.8.4
SRCREV = "ddabf50f72cf369bf652a95c4d9fe31a1865a781"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON -DJSONCPP_WITH_PKGCONFIG_SUPPORT=OFF -DBUILD_TESTING=OFF -DJSONCPP_WITH_TESTS=OFF"

inherit cmake

BBCLASSEXTEND_append += " nativesdk"
