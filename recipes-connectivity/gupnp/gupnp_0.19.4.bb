require recipes-connectivity/gupnp/gupnp.inc

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.19/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "516549cebc5af416f2fd7177d6512a0f"
SRC_URI[sha256sum] = "3c1fb2873cb2f8f152b9d49598d55b15b8f3c1096a03ade46ce69694798fb243"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://libgupnp/gupnp.h;beginline=1;endline=20;md5=d78a69d9b6e63ee2dc72e7b674d97520"

DEFAULT_PREFERENCE = "-1"
