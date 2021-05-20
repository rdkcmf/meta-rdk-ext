#
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Simple C program to allocate memory from the command line. Useful to test programs or systems under high memory usage conditions"
HOMEPAGE = "https://github.com/julman99/eatmemory"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34eef6c3e97eb6ce4fade54895597de8"
SECTION = "apps"
DEPENDS = ""

SRC_URI = "git://github.com/julman99/eatmemory.git"
SRCREV = "7c26068682748b071d9184f329248d5036476041"

S = "${WORKDIR}/git"

do_install () {
	install -d ${D}${bindir}
	oe_runmake PREFIX=${D}${prefix} install
}
