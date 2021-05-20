SUMMARY = "portable Universal Plug and Play SDK"
HOMEPAGE = "http://pupnp.sourceforge.net/"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRC_URI = "http://sourceforge.net/projects/pupnp/files/pupnp/libUPnP%20${PV}/${BPN}-${PV}.tar.bz2 \
          file://out-of-tree_${PV}.patch \
          file://private-nets_${PV}.patch \
          "

EXTRA_OECONF += "--disable-blocking_tcp_connections --disable-static --enable-ipv6 --disable-notification_reordering"

SRC_URI[md5sum] = "530e91e96119ee32a9523a73572b8d8f"
SRC_URI[sha256sum] = "0bdfacb7fa8d99b78343b550800ff193264f92c66ef67852f87f042fd1a1ebbc"

inherit autotools-brokensep pkgconfig
