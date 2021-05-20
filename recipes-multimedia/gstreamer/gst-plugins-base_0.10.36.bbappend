EXTRA_OECONF += " \
             --disable-examples --disable-x --disable-xvideo --disable-xshm \
             --disable-oggtest --disable-gtk-doc \
             --disable-cdparanoia --disable-gnome_vfs  \
             --disable-pango --disable-vorbistest --disable-freetypetest --disable-gdp \
             --disable-adder --disable-app --disable-audiorate \
             --disable-tcp  --disable-videorate  \
             --enable-iso-codes --enable-subparse --enable-typefind \
             "

# notes:
# --disable-orc is in OE, not in Entropic SDK
#
# the following were in Entropic SDK, however they break the Gst meta
# packages from OE. For now, we disable them
# --disable-alsa 
# --disable-videotestsrc
# --disable-videoscale
# --disable-audiotestsrc
# --disable-ffmpegcolorspace
# --disable-theora
# --disable-volume
# --disable-audioconvert
#
# EXTRA_OECONF += "--disable-freetypetest --disable-pango --disable-gnome_vfs --disable-orc"
# EXTRA_OECONF = "--disable-valgrind ${GSTREAMER_DEBUG} --disable-examples "
#
# option_configure_args := --disable-examples --disable-x --disable-xvideo --disable-xshm \
# --disable-oggtest --disable-audiotestsrc --disable-gtk-doc --disable-audioresample --disable-audioconvert \
# --disable-cdparanoia --disable-gnome_vfs --disable-theora --disable-ffmpegcolorspace \
# --disable-pango --disable-vorbistest --disable-freetypetest --disable-gdp \
# --disable-alsa --disable-adder --disable-app --disable-audiorate \
# --disable-tcp --disable-videotestsrc --disable-videorate --disable-videoscale --disable-volume \
# --enable-iso-codes --enable-subparse --enable-typefind

#FILES_${PN} += " ${libdir}/gstreamer-0.10/*"
#FILES_${PN}-dev += " ${libdir}/gstreamer-0.10/*"
#FILES_${PN}-dbg += " ${libdir}/gstreamer-0.10/.debug/ ${libexecdir}/gstreamer-0.10/.debug/"
#FILES_${PN} += "${bindir}/*"


# Fix for the iHeart Radio
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append += "file://gst-plugins-base-appsrc-caps-fix-0.10.36.patch"

# Remove unwanted libs
EXTRA_OECONF += " --disable-audiotestsrc --disable-videotestsrc"

