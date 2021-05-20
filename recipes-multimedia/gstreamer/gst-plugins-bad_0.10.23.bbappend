FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

EXTRA_OECONF += " \              
              --disable-examples --disable-adpcmdec --disable-adpcmenc --disable-aiff --disable-asfmux \
              --disable-autoconvert --disable-camerabin --disable-legacyresample --disable-bayer --disable-cdxaparse --disable-dataurisrc \
              --disable-dccp --disable-debugutils --disable-dtmf --disable-dvdspu --disable-festival --disable-freeze --disable-frei0r --disable-gtk-doc \
              --disable-h264parse --disable-hdvparse --disable-id3tag --disable-jpegformat --disable-librfb --disable-liveadder \
              --disable-mpegtsmux --disable-mpegpsmux --disable-mve --disable-mxf \
              --disable-nsf --disable-nuvdemux --disable-pcapparse --disable-pnm --disable-rawparse --disable-real --disable-rtpmux \
              --disable-scaletempo --disable-sdp --disable-segmentclip --disable-siren --disable-speed --disable-subenc --disable-stereo --disable-tta \
              --disable-videomeasure --disable-videosignal --disable-vmnc --disable-directsound --disable-directdraw --disable-osx_video \
              --disable-quicktime --disable-vcd --disable-assrender --disable-apexsink --disable-bz2 --disable-cdaudio \
              --disable-celt --disable-cog --disable-dc1394 --disable-directfb --disable-dirac --disable-dts --disable-divx --disable-resindvd \
              --disable-faac --disable-faad --disable-flite --disable-gsm --disable-jp2k --disable-kate \
              --disable-ladspa --disable-lv2 --disable-libmms --disable-modplug --disable-mimic --disable-mpeg2enc --disable-mplex --disable-musepack \
              --disable-musicbrainz --disable-mythtv --disable-nas --disable-ofa --disable-rsvg --disable-timidity --disable-wildmidi \
              --disable-sdl --disable-sdltest --disable-sndfile --disable-soundtouch --disable-spc --disable-gme --disable-swfdec \
              --disable-xvid --disable-dvb --disable-wininet --disable-acm --disable-vdpau --disable-schro --disable-zbar --disable-vp8 --disable-neon \
              --disable-nls --disable-rtmp --disable-external --disable-dependency-tracking --disable-audiovisualizers --disable-camerabin2 \
              --disable-coloreffects --disable-colorspace --disable-dvbsuboverlay --disable-faceoverlay --disable-fieldanalysis \
              --disable-freeverb --disable-gaudieffects --disable-geometrictransform --disable-hls --disable-inter  \
              --disable-interlace --disable-jp2kdecimator --disable-mpegdemux --disable-mpegtsdemux \
              --disable-mpegvideoparse --disable-patchdetect --disable-removesilence --disable-rtpvp8 \
              --disable-sdi --disable-smooth --disable-videofilters --disable-videomaxrate --disable-videoparsers --disable-y4m \
              "
