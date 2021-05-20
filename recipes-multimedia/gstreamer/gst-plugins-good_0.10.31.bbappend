FILESEXTRAPATHS_prepend := "${THISDIR}/files:"


EXTRA_OECONF += " \
              --disable-x --disable-xvideo --disable-xshm --disable-gtk-doc \
              --disable-jpeg --disable-libcaca --disable-libdv --disable-libpng --disable-pulse --disable-dv1394 \
              --disable-shout2 --disable-speex --disable-gconftool --disable-cairo \
              --disable-esd --disable-esdtest --disable-gconf --disable-gdk_pixbuf \
              --disable-hal --disable-gst_v4l2 --disable-examples --disable-videofilter --disable-alpha \
              --disable-apetag --disable-audiofx --disable-auparse --disable-cutter \
              --disable-deinterlace --disable-effectv --disable-equalizer \
              --disable-interleave --disable-flx --disable-goom --disable-goom2k1 \
              --disable-imagefreeze --disable-law --disable-level --disable-monoscope --disable-multifile --disable-multipart \
              --disable-replaygain --disable-shapewipe \
              --disable-smpte --disable-spectrum --disable-videobox --disable-videocrop --disable-videomixer \
              --disable-wavenc --enable-wavparse --disable-y4m --disable-directsound --disable-oss --disable-oss4 \
              --disable-sunaudio --disable-osx_audio --disable-osx_video --disable-aalib --disable-aalibtest --disable-annodex \
              --disable-taglib --disable-wavpack --disable-bz2 --enable-flac --enable-soup \
              --enable-nls --enable-avi --enable-flv --enable-matroska --enable-zlib --enable-id3demux --enable-icydemux \
              --enable-audioparsers \
             "

PACKAGECONFIG[isomp4]  = "--enable-isomp4,--disable-isomp4"

PACKAGECONFIG += " isomp4"

# notes:
#
# the following were in Entropic SDK, however they break the Gst meta
# packages from OE. For now, we disable them
# --disable-autodetect 
# --disable-debugutils
#
# EXTRA_OECONF = "--disable-valgrind ${GSTREAMER_DEBUG} --disable-examples "
# 
# PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)} jpeg"
# PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"
# PACKAGECONFIG[jack] = "--enable-jack,--disable-jack,jack"
# PACKAGECONFIG[jpeg] = "--enable-jpeg,--disable-jpeg,jpeg"
# PACKAGECONFIG[wavpack] = "--enable-wavpack,--disable-wavpack,wavpack"
# PACKAGECONFIG[gdkpixbuf] = "--enable-gdk_pixbuf,--disable-gdk_pixbuf,gdk-pixbuf"
# PACKAGECONFIG[v4l] = "--with-libv4l2,--without-libv4l2,libv4l"
# PACKAGECONFIG[bzip2] = "--enable-bz2,--disable-bz2,bzip2"
# PACKAGECONFIG[orc] = "--enable-orc,--disable-orc,orc"
# 
# EXTRA_OECONF += "--disable-aalib --disable-esd --disable-shout2 --disable-libcaca --disable-hal --without-check \
#                  --disable-orc --disable-examples --disable-taglib"
# 
# option_configure_args := --disable-x --disable-xvideo --disable-xshm --disable-gtk-doc \
# --disable-jpeg --disable-libcaca --disable-libdv --disable-libpng --disable-pulse --disable-dv1394 \
# --disable-shout2 --disable-shout2test --disable-speex --disable-gconftool --disable-cairo \
# --disable-esd --disable-esdtest --disable-gconf --disable-gdk_pixbuf --disable-gdk_pixbuf3 \
# --disable-hal --disable-gst_v4l2 --disable-examples --disable-videofilter --disable-alpha \
# --disable-apetag --disable-audiofx --disable-auparse --disable-autodetect --disable-cutter \
# --disable-debugutils --disable-deinterlace --disable-effectv --disable-equalizer \
# --disable-icydemux --disable-interleave --disable-flx --disable-goom --disable-goom2k1 \
# --disable-imagefreeze --disable-law --disable-level --disable-monoscope --disable-multifile --disable-multipart \
# --disable-replaygain --disable-shapewipe \
# --disable-smpte --disable-spectrum --disable-videobox --disable-videocrop --disable-videomixer \
# --disable-wavenc --enable-wavparse --disable-y4m --disable-directsound --disable-oss --disable-oss4 \
# --disable-sunaudio --disable-osx_audio --disable-osx_video --disable-aalib --disable-aalibtest --disable-annodex \
# --disable-taglib --disable-wavpack --disable-bz2 --enable-flac --enable-soup \
# --enable-nls --enable-avi --enable-flv --enable-matroska --enable-qtdemux --enable-zlib --enable-id3demux \
# --enable-audioparsers

#FILES_${PN} += " ${libdir}/gstreamer-0.10/*.so"
#FILES_${PN}-dev += " ${libdir}/gstreamer-0.10/*.la ${libdir}/gstreamer-0.10/*.a"
#FILES_${PN}-dbg += " ${libdir}/gstreamer-0.10/.debug/ ${libexecdir}/gstreamer-0.10/.debug/"
#FILES_${PN} += "${bindir}/*"
