SUMMARY = "Yet Another JSON Library - A Portable JSON parsing and serialization library in ANSI C"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3c388455eccfb12636b7af50a1eb563c"

inherit cmake

# corresponds to tag 1.0.9
SRCREV = "9c15d72751d12552fa70f30b7a579b8150653321"
SRC_URI = "git://github.com/lloyd/${BPN}"
SRC_URI += "file://0001-Fix-compile-error.patch"
SRC_URI += "file://yajl_generic_v1.0.9.patch"
SRC_URI += "file://0001-math-link.patch"

S = "${WORKDIR}/git"

