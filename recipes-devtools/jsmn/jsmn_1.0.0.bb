SUMMARY = "This recipes is used to compile and install Jsmn"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5adc94605a1f7a797a9a834adbe335e3"

SRC_URI = "https://github.com/zserge/jsmn/archive/v1.0.0.tar.gz \
           file://CMakeLists.txt \
	  "

SRCREV_jsmn = "${AUTOREV}"
SRCREV_FORMAT = "jsmn"

SRC_URI[md5sum] = "54b90eec2f5ab33d6794cfb47806b327"
SRC_URI[sha256sum] = "5b1f46312cf205e6a0923a0656893bde9e022c76d06d980958fa8dbf79f0ea06"

S = "${WORKDIR}/jsmn-1.0.0"

do_configure_prepend() {
cp ${WORKDIR}/CMakeLists.txt ${S}
}

inherit cmake coverity

INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN} += "build-deps"

FILES_${PN} += "/usr/*"

ALLOW_EMPTY_${PN} = "1"

