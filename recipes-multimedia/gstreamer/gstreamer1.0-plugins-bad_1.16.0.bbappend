require gstreamer1.0-plugins-bad_srt.inc

PACKAGECONFIG[srt] = "--enable-srt,--disable-srt,srt"
PACKAGECONFIG[srtp] = "--enable-srtp,--disable-srtp,libsrtp"

EXTRA_OEMESON += " \
--enable-srt \
"
