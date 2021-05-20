
SUMMARY = "Ultralightweight JSON parser in ANSI C"
HOMEPAGE = "https://github.com/DaveGamble/cJSON"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "git://github.com/DaveGamble/cJSON.git;destsuffix=${S};protocol=https"
SRCREV = "7cc52f60356909b3dd260304c7c50c0693699353"

inherit cmake pkgconfig

LDFLAGS += "-lm"

FILES_${PN}-dev += "${libdir}/cmake"

