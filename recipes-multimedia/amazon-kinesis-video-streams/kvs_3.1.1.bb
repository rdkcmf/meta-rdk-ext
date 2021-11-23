SUMMARY = "This recipes is used to compile and install KVS 2.0.2 SDK"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34400b68072d710fecd0a2940a0d1658"

SRC_URI = "git://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp;Tag=3.1.1;protocol=https \
           file://kvs_3.1.1_cmake.patch \
	   file://kvscproducer_cmake.patch;apply=no \
          "

SRCREV = "2d653a29451bce60741a4aeebb5825518345d25f"

DEPENDS = " gtest-camera log4cplus-camera jsmn gstreamer1.0 gstreamer1.0-plugins-base glib-2.0 curl cjson"

S = "${WORKDIR}/git"

#inherit autotools
inherit cmake coverity

do_configure_prepend() {

cp ${WORKDIR}/kvscproducer_cmake.patch ${S}/CMake/Dependencies/
}

INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN} += "build-deps"

FILES_${PN} += "/usr/*"

ALLOW_EMPTY_${PN} = "1"

do_install_append() {

    install -d ${D}${libdir}
    install -m 0755 ${WORKDIR}/build/dependency/libkvscproducer/kvscproducer-src/libcproducer.so ${D}${libdir}

}
