SUMMARY = "This recipes is used to compile and install KVS 2.0.2 SDK"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

SRC_URI = "git://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp;Tag=2.0.2;protocol=https \
           file://CMakeLists.txt \
           file://kvs-cmake.patch \
          "

SRCREV = "116b9041e111705c05637920091b02da6b63256b"

SRC_URI[md5sum] = "0e8b7ef0cea124e3e3e15f7925d25421"
SRC_URI[sha256sum] = "321f756ca2aec6388a982fb1816ba504e2cdd8984715f688260be00bf9d21c52"

DEPENDS = " gtest-camera log4cplus-camera jsmn gstreamer1.0 gstreamer1.0-plugins-base glib-2.0 curl cjson"

S = "${WORKDIR}/git"

do_configure_prepend() {
cp ${WORKDIR}/CMakeLists.txt ${S}
}

#inherit autotools
inherit cmake coverity

INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN} += "build-deps"

FILES_${PN} += "/usr/*"

ALLOW_EMPTY_${PN} = "1"
