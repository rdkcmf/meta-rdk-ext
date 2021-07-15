DESCRIPTION = "Reference gst external plugin"
LICENSE = "CLOSED"

DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base westeros-sink"

S = "${WORKDIR}"

inherit pkgconfig

SRC_URI = " \
  file://Makefile \
  file://gst_external_plugin.c \
  file://gst_external_sink.c \
  file://gst_external_sink.h \
  file://gst_external_src.c \
  file://gst_external_src.h \
"
SRCREV = "${AUTOREV}"

EXT_SRC_PROTOCOL ?= "rec"
CFLAGS += "-DGST_EXT_SRC_PROTOCOL=${EXT_SRC_PROTOCOL}"

do_install_append() {
  make DESTDIR=${D} install
}

FILES_${PN} += "*"
