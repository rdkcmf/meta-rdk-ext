SUMMARY = "Ultralightweight JSON parser in ANSI C"
HOMEPAGE = "https://github.com/DaveGamble/cJSON"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed85eba7edec076e4a49e6ee38b0e981"

PR = "r1"

SRCREV = "aafb64a1c549b7b927e339df6d35b1d5059dc235"

SRC_URI = "git://github.com/DaveGamble/cJSON.git"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

LDFLAGS += "-lm"

FILES_${PN}-dev += "${libdir}/cmake"

