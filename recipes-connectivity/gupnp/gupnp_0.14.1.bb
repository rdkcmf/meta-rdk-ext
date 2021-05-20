require recipes-connectivity/gupnp/gupnp.inc

SRC_URI = "http://ftp.gnome.org/mirror/gnome.org/sources/gupnp/0.14/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "47d0eb5997d4f08713dce6b2c4e47f80"
SRC_URI[sha256sum] = "8a893753ad8999c5cd3de60a17624320103158486fa23975ef1fb8175f3ebde0"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://libgupnp/gupnp.h;beginline=1;endline=20;md5=28c49b17d623afc3335efc2e511879e1"

DEFAULT_PREFERENCE = "-1"
