require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2+ & LGPLv2.1+ "

DEPENDS += "gstreamer1.0-plugins-base bzip2"
RDEPENDS_${PN} += "nettle"
RDEPENDS_${PN}_remove_daisy = "nettle"

S = "${WORKDIR}/gst-plugins-bad-${PV}"

inherit gettext
BLUEZ ?= "${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', bb.utils.contains('DISTRO_FEATURES', 'bluez5', 'bluez5', 'bluez4', d), '', d)}"


PACKAGECONFIG ??= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'eglgles', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'enable_firebolt_compliance_tdk', '', 'orc', d)} \
    curl uvch264 neon dataurisrc \
    hls sbc dash bz2 smoothstreaming \
    "
# dash = Dynamic Adaptive Streaming over HTTP
PACKAGECONFIG[assrender]       = "--enable-assrender,--disable-assrender,libass"
PACKAGECONFIG[curl]            = "--enable-curl,--disable-curl,curl"
# PACKAGECONFIG[eglgles]         = "--enable-eglgles,--disable-eglgles,virtual/egl virtual/libgles2"
PACKAGECONFIG[faac]            = "--enable-faac,--disable-faac,faac"
PACKAGECONFIG[faad]            = "--enable-faad,--disable-faad,faad2"
PACKAGECONFIG[libmms]          = "--enable-libmms,--disable-libmms,libmms"
PACKAGECONFIG[modplug]         = "--enable-modplug,--disable-modplug,libmodplug"
PACKAGECONFIG[opus]            = "--enable-opus,--disable-opus,libopus"
PACKAGECONFIG[flite]           = "--enable-flite,--disable-flite,flite-alsa"
PACKAGECONFIG[opencv]          = "--enable-opencv,--disable-opencv,opencv"
PACKAGECONFIG[wayland]         = "--enable-wayland,--disable-wayland,wayland"
PACKAGECONFIG[uvch264]         = "--enable-uvch264,--disable-uvch264,libusb1 udev"
PACKAGECONFIG[directfb]        = "--enable-directfb,--disable-directfb,directfb"
PACKAGECONFIG[neon]            = "--enable-neon,--disable-neon,neon"
PACKAGECONFIG[openal]          = "--enable-openal,--disable-openal,openal-soft"
PACKAGECONFIG[hls]             = "--enable-hls,--disable-hls,gnutls"
PACKAGECONFIG[sbc]             = "--enable-sbc,--disable-sbc,sbc"
PACKAGECONFIG[dash]            = "--enable-dash,--disable-dash,libxml2"
PACKAGECONFIG[bz2]             = "--enable-bz2,--disable-bz2,bzip2"
PACKAGECONFIG[fluidsynth]      = "--enable-fluidsynth,--disable-fluidsynth,fluidsynth"
PACKAGECONFIG[schroedinger]    = "--enable-schro,--disable-schro,schroedinger"
PACKAGECONFIG[smoothstreaming] = "--enable-smoothstreaming,--disable-smoothstreaming,libxml2"
PACKAGECONFIG[bluez]           = "--enable-bluez,--disable-bluez,${BLUEZ}"
PACKAGECONFIG[rsvg]            = "--enable-rsvg,--disable-rsvg,librsvg"
PACKAGECONFIG[dataurisrc]      = "--enable-dataurisrc,--disable-dataurisrc"

# these plugins have not been ported to 1.0 (yet):
#   directdraw vcd apexsink cdaudio dc1394 lv2 linsys musepack mythtv
#   nas timidity teletextdec sdl sndfile xvid wininet acm gsettings
#   sndio cdxaparse dccp faceoverlay hdvparse ivfparse jp2kdecimator
#   linsys mve nuvdemux osx_video patchdetect quicktime real sdi stereo
#   tta videomeasure videosignal vmnc

EXTRA_OECONF += " \
    --disable-dvb \
    --enable-fbdev \
    --disable-decklink \
    --disable-acm \
    --disable-android_media \
    --disable-apexsink \
    --disable-apple_media \
    --disable-avc \
    --disable-chromaprint \
    --disable-daala \
    --disable-dc1394 \
    --disable-direct3d \
    --disable-directsound \
    --disable-dts \
    --disable-gme \
    --disable-gsm \
    --disable-kate \
    --disable-ladspa \
    --disable-linsys \
    --disable-lv2 \
    --disable-mimic \
    --disable-mpeg2enc \
    --disable-mplex \
    --disable-musepack \
    --disable-nas \
    --disable-ofa \
    --disable-openjpeg \
    --disable-opensles \
    --disable-pvr \
    --disable-resindvd \
    --disable-rtmp \
    --disable-sdl \
    --disable-sdltest \
    --disable-sndfile \
    --disable-sndio \
    --disable-soundtouch \
    --disable-spandsp \
    --disable-spc \
    --disable-srtp \
    --disable-teletextdec \
    --disable-timidity \
    --disable-vcd \
    --disable-vdpau \
    --disable-voaacenc \
    --disable-voamrwbenc \
    --disable-wasapi \
    --disable-webp \
    --disable-wildmidi \
    --disable-wininet \
    --disable-winscreencap \
    --disable-xvid \
    --disable-zbar \
    ${GSTREAMER_1_0_ORC} \
    "

ARM_INSTRUCTION_SET = "arm"

## Disabled everything from bad plugin except ivfparse

EXTRA_OECONF += " \
                --disable-accurip --disable-adpcmdec --disable-adpcmenc --disable-aiff --disable-asfmux --disable-audiofxbad --disable-audiomixer \
                --disable-autoconvert --disable-bayer --disable-compositor \
                --disable-dvbsuboverlay --disable-dvdspu --disable-festival --disable-fieldanalysis --disable-freeverb --disable-frei0r --disable-gdp \
                --disable-geometrictransform --disable-id3tag --disable-inter --disable-interlace --disable-ivtc --disable-jp2kdecimator --disable-jpegformat --disable-librfb \
                --disable-midi --disable-mpegdemux --disable-mpegpsmux --disable-mpegtsmux --disable-mxf --disable-pcapparse \
                --disable-pnm --disable-rawparse --disable-removesilence --disable-sdp --disable-segmentclip --disable-siren --disable-speed \
                --disable-stereo --disable-subenc --disable-videosignal --disable-vmnc --disable-yadif \
                "

#Enabling debugutils while firebolt_compliance_tdk is enabled
EXTRA_OECONF += "${@bb.utils.contains_any('DISTRO_FEATURES', 'enable_firebolt_compliance_tdk benchmark_enable','--enable-debugutils', '--disable-debugutils', d)}"


EXTRA_OECONF += " --enable-ivfparse --enable-external"

## For MSE Integration Temproray fix
EXTRA_OECONF += " --enable-videoparsers"
