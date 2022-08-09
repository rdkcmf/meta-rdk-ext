include gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=27db269c575d1e5317fffca2d33b3b50"
SRC_URI += "file://0001-tsdemux-add-the-UNSELECT-stream-flag-to-non-default-.patch"
SRC_URI += "${@bb.utils.contains('DISTRO_FEATURES', 'sage_svp', 'file://0007-videoparser-remove-h264-h265parse-for-svp.patch', '', d)}"
SRC_URI += "file://0002-h264parse-always-send-codec-data-updates.patch"
SRC_URI += "file://make43.patch"
SRC_URI += "file://0001-RDK-30521-h265-parser-patch-to-embed-SEI-timecodes.patch"
SRC_URI += "file://gst-plugins-bad-0001-h264parse-Post-a-WARNING-when-data-is-broken.patch"
SRC_URI[md5sum] = "2757103e57a096a1a05b3ab85b8381af"
SRC_URI[sha256sum] = "23ddae506b3a223b94869a0d3eea3e9a12e847f94d2d0e0b97102ce13ecd6966"
S = "${WORKDIR}/gst-plugins-bad-${PV}"

EXTRA_OECONF += "--enable-introspection=no"
