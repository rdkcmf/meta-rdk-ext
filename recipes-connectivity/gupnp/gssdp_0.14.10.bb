include recipes-connectivity/gupnp/gssdp.inc
LICENSE = "LGPLv2"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/${BPN}/0.14/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "bd355c3be1618390172c1f63e5afe58b"
SRC_URI[sha256sum] = "b9b3be6354d6a0dcc7c29a7da4120847350011e5614383626552e15c28f3058c"

FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"
SRC_URI += "file://delia-6118.patch"
SRC_URI += "file://increase_rescan_timeout.patch"

EXTRA_OECONF += "--enable-introspection=no"
