SUMMARY = "Static Multicast Routing Daemon v2.3.1"
DESCRIPTION = "SMCRoute is a daemon and command line tool to manipulate the multicast routing table in the UNIX kernel."
HOMEPAGE = "http://troglobit.github.io/smcroute.html"
SECTION = "console/network"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SRCREV = "eb8731a8196aa3359f2ef7871d3823762fde3716"
SRC_URI = "git://github.com/troglobit/smcroute.git;branch=master;protocol=git"

S = "${WORKDIR}/git"
inherit autotools

INSANE_SKIP_${PN} += " installed-vs-shipped"
