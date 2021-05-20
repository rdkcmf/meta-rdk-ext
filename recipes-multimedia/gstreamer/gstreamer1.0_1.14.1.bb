require gstreamer1.0.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6762ed442b3822387a51c92d928ead0d \
                    file://gst/gst.h;beginline=1;endline=21;md5=e059138481205ee2c6fc1c079c016d0d"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gstreamer/gstreamer-${PV}.tar.xz \
"

SRC_URI_append = " \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://gtk-doc-tweaks.patch \
    file://0001-gst-gstpluginloader.c-when-env-var-is-set-do-not-fal.patch \
"

PACKAGECONFIG ??= ""

PACKAGECONFIG[debug] = "--enable-debug,--disable-debug"
PACKAGECONFIG[tests] = "--enable-tests,--disable-tests"
PACKAGECONFIG[valgrind] = "--enable-valgrind,--disable-valgrind,valgrind,"
PACKAGECONFIG[gst-tracer-hooks] = "--enable-gst-tracer-hooks,--disable-gst-tracer-hooks,"
PACKAGECONFIG[unwind] = "--with-unwind,--without-unwind,libunwind"
PACKAGECONFIG[dw] = "--with-dw,--without-dw,elfutils"


SRC_URI[md5sum] = "c46967bd2b7b6b73fa4f47de61610243"
SRC_URI[sha256sum] = "28d82b0d261544a9bf85b429399929e4986eb00efcf1ce16cc71d269a4c3186c"

S = "${WORKDIR}/gstreamer-${PV}"

CVE_PRODUCT = "gstreamer"
