require gstreamer1.0-plugins-bad_srt.inc

PACKAGECONFIG[srt] = "-Dsrt=enabled,-Dsrt=disabled,srt"
PACKAGECONFIG[srtp] = "-Dsrtp=enabled,-Dsrtp=disabled,libsrtp"

EXTRA_OEMESON += " \
-Dsrt=enabled \
"
