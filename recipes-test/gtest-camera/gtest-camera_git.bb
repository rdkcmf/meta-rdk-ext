SUMMARY = "This recipes is used to compile and install Google test component"

LICENSE = "BSD-3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbbd27594afd089daa160d3a16dd515a"

SRCREV = "3cf8f514d859d65b7202e51c662a03a92887b8e2"

SRC_URI = "git://github.com/rdkcteam/googletest;protocol=https \
           file://gtest-camera-cmake.patch \
          "

S = "${WORKDIR}/git"

inherit cmake coverity

INSANE_SKIP_${PN}-dev += "dev-elf"

FILES_${PN} += "/usr/*"

ALLOW_EMPTY_${PN} = "1"
