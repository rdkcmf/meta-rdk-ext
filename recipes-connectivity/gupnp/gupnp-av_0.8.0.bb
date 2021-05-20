require recipes-connectivity/gupnp/gupnp-av.inc

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/${BPN}/0.8/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "8a41a3bb60b50fceb5ece71c0dc4dcfb"
SRC_URI[sha256sum] = "e6aa032377488cedc4e347519bd30701c583780b7c54f00bab083f2316a93dc9"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://libgupnp-av/gupnp-av.h;beginline=1;endline=22;md5=d344132a8766641fcb11213ff5982086"

EXTRA_OECONF += "--enable-introspection=no"
