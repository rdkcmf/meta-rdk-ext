DESCRIPTION = "Reference gst external plugin"
LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM = "file://../../COPYING;md5=505a896dcb5794959ca509c917a0ca0c"

DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base westeros-sink"

inherit autotools pkgconfig

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/gst-plugins-rdk;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_BRANCH}"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/src/external"

EXT_SRC_PROTOCOL ?= "rec"
CFLAGS += "-DGST_EXT_SRC_PROTOCOL=${EXT_SRC_PROTOCOL}"

FILES_${PN} += "*"
