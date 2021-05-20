LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b980284408dfdf6b4e464f4cb4233e06"

SRC_URI = "git://github.com/aws/aws-iot-device-sdk-embedded-C.git;protocol=https;branch=v4_beta_deprecated"
SRC_URI += "file://0001-Add-install-for-targets.patch"
SRC_URI += "file://0002-use-hints-for-dns.patch"
SRC_URI += "file://iot_config.h"

SRCREV = "1ad675b8532f329bebaf40cda54192d0cb4feecb"

S = "${WORKDIR}/git"

DEPENDS = "openssl"

EXTRA_OECMAKE = "-DIOT_NETWORK_USE_OPENSSL=ON -DAWS_IOT_MQTT_ENABLE_METRICS=0 -DGIT_EXECUTABLE=/usr/bin/git"

inherit cmake pkgconfig

do_configure_prepend () {
    cp -f ${WORKDIR}/iot_config.h ${S}/demos/iot_config.h
}

do_install_append () {

    install -m 644 ${WORKDIR}/iot_config.h ${D}/usr/include/awsiot
}

FILES_SOLIBSDEV = ""
FILES_${PN} =+ " ${libdir}/lib*.so*"
FILES_${PN}-dbg =+ " ${libdir}/.debug/lib*.so*"

