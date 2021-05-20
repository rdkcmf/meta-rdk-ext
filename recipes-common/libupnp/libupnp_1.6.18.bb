SUMMARY = "portable Universal Plug and Play SDK"
HOMEPAGE = "http://pupnp.sourceforge.net/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRC_URI = "http://sourceforge.net/projects/pupnp/files/pupnp/libUPnP%20${PV}/${BPN}-${PV}.tar.bz2 \
           file://out-of-tree.patch \
"

EXTRA_OECONF += "--disable-blocking_tcp_connections --disable-static --enable-ipv6 --disable-notification_reordering"

SRC_URI[md5sum] = "11c6484fd2e2927bf3b8d8108407ca56"
SRC_URI[sha256sum] = "b21bc676365622d3ace1b25292dab8d4d23f6e6a80ddc8f029b765d39797e934"


inherit autotools-brokensep pkgconfig


