require gstreamer1.0-plugins-bad_srt.inc

PACKAGECONFIG += "${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'sbc', '', d)}"
PACKAGECONFIG[srt] = "-Dsrt=enabled,-Dsrt=disabled,srt"
PACKAGECONFIG[srtp] = "-Dsrtp=enabled,-Dsrtp=disabled,libsrtp"
PACKAGECONFIG[sbc] = "-Dsbc=enabled,-Dsbc=disabled,sbc"

EXTRA_OEMESON += " \
-Dsrt=enabled \
${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', '-Dsbc=enabled', '', d)} \
"
