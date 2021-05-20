SUMMARY = "WebKit for Wayland port pairs the WebKit engine with the Wayland display protocol, \
           allowing embedders to create simple and performant systems based on Web platform technologies. \
           It is designed with hardware acceleration in mind, relying on EGL, the Wayland EGL platform, and OpenGL ES."
HOMEPAGE = "http://www.webkitforwayland.org/"
LICENSE = "BSD-2-Clause & BSD-3-Clause & LGPL-2.0 & LGPL-2.1"
LIC_FILES_CHKSUM = "file://Source/WebCore/LICENSE-LGPL-2.1;md5=a778a33ef338abbaf8b8a7c36b6eec80 "

CMAKE_VERBOSE = ""
DEPENDS += " \
     libwpe \
     bison-native ccache-native glib-2.0-native gperf-native libxml2-native ninja-native ruby-native chrpath-replacement-native \
     cairo freetype glib-2.0 gnutls harfbuzz icu jpeg pcre sqlite3 zlib \
     libepoxy libpng libsoup-2.4 libwebp libxml2 libxslt \
     virtual/egl virtual/libgles2 \
     libinput westeros westeros-sink \
"
DEPENDS_remove_morty = " libwpe "
DEPENDS_remove_daisy = " libwpe "

RDEPENDS_${PN} += "injectedbundle tts"

EXTRANATIVEPATH += " chrpath-native"

S = "${WORKDIR}/git"

inherit cmake pkgconfig perlnative pythonnative

WPE_BACKEND ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'westeros', '', d)}"

# The libprovision prebuilt libs currently support glibc ARM only.
PROVISIONING ?= "provisioning"
PROVISIONING_libc-musl = ""
PROVISIONING_mipsel = ""
PROVISIONING_x86 = ""

PACKAGECONFIG ?= "2dcanvas cssshapes fetchapi full_logs mediasource encryptedmedia performance-timeline picturesize \
                  promise ${PROVISIONING} resourcetiming video ${WPE_BACKEND} mediastream native_audio sampling-profiler \
                  ${@bb.utils.contains('DISTRO_FEATURES', 'opencdm', 'wpeframework_opencdm', '', d)}"
PACKAGECONFIG_remove_libc-musl = "sampling-profiler"

# WPE features
PACKAGECONFIG[2dcanvas] = "-DENABLE_ACCELERATED_2D_CANVAS=ON,-DENABLE_ACCELERATED_2D_CANVAS=OFF,"
PACKAGECONFIG[cssshapes] = "-DENABLE_CSS_SHAPES=ON,-DENABLE_CSS_SHAPES=OFF,"
PACKAGECONFIG[deviceorientation] = "-DENABLE_DEVICE_ORIENTATION=ON,-DENABLE_DEVICE_ORIENTATION=OFF,"
PACKAGECONFIG[fetchapi] = "-DENABLE_FETCH_API=ON,-DENABLE_FETCH_API=OFF,"
PACKAGECONFIG[fullscreenapi] = "-DENABLE_FULLSCREEN_API=ON,-DENABLE_FULLSCREEN_API=OFF,"
PACKAGECONFIG[full_logs] = "-DENABLE_LOGS=ON,-DENABLE_LOGS=OFF,"
PACKAGECONFIG[fusion] = "-DUSE_FUSION_API_GSTREAMER=ON,-DUSE_FUSION_API_GSTREAMER=OFF,"
PACKAGECONFIG[gamepad] = "-DENABLE_GAMEPAD=ON,-DENABLE_GAMEPAD=OFF,"
PACKAGECONFIG[geolocation] = "-DENABLE_GEOLOCATION=ON,-DENABLE_GEOLOCATION=OFF,"
PACKAGECONFIG[highdpicanvas] = "-DENABLE_HIGH_DPI_CANVAS=ON,-DENABLE_HIGH_DPI_CANVAS=OFF,"
PACKAGECONFIG[indexeddb] = "-DENABLE_DATABASE_PROCESS=ON -DENABLE_INDEXED_DATABASE=ON,-DENABLE_DATABASE_PROCESS=OFF -DENABLE_INDEXED_DATABASE=OFF,"
PACKAGECONFIG[inputspeech] = "-DENABLE_INPUT_SPEECH=ON,-DENABLE_INPUT_SPEECH=OFF,"
PACKAGECONFIG[intl] = "-DENABLE_INTL=ON,-DENABLE_INTL=OFF,"
PACKAGECONFIG[mediasource] = "-DENABLE_MEDIA_SOURCE=ON,-DENABLE_MEDIA_SOURCE=OFF,gstreamer1.0 gstreamer1.0-plugins-good,${RDEPS_MEDIASOURCE}"
PACKAGECONFIG[mediastream] ?= "-DENABLE_MEDIA_STREAM=ON -DENABLE_WEB_RTC=ON,-DENABLE_MEDIA_STREAM=OFF -DENABLE_WEB_RTC=OFF,qtwebrtc,qtwebrtc"
PACKAGECONFIG[notifications] = "-DENABLE_NOTIFICATIONS=ON,-DENABLE_NOTIFICATIONS=OFF,"
PACKAGECONFIG[performance-timeline] = "-DENABLE_PERFORMANCE_TIMELINE=ON,-DENABLE_PERFORMANCE_TIMELINE=OFF,"
PACKAGECONFIG[picturesize] = "-DENABLE_PICTURE_SIZES=ON,-DENABLE_PICTURE_SIZES=OFF,"
PACKAGECONFIG[promise] = "-DENABLE_PROMISES=ON,-DENABLE_PROMISES=OFF,"
PACKAGECONFIG[resourcetiming] = "-DENABLE_RESOURCE_TIMING=ON,-DENABLE_RESOURCE_TIMING=OFF,"
PACKAGECONFIG[scriptedspeech] = "-DENABLE_SCRIPTED_SPEECH=ON,-DENABLE_SCRIPTED_SPEECH=OFF,"
PACKAGECONFIG[suidsandboxlinux] = "-DENABLE_SUID_SANDBOX_LINUX=ON,-DENABLE_SUID_SANDBOX_LINUX=OFF,"
PACKAGECONFIG[sampling-profiler] = "-DENABLE_SAMPLING_PROFILER=ON,-DENABLE_SAMPLING_PROFILER=OFF,"
PACKAGECONFIG[shadowdom] = "-DENABLE_SHADOW_DOM=ON,-DENABLE_SHADOW_DOM=OFF,"
PACKAGECONFIG[subtlecrypto] = "-DENABLE_SUBTLE_CRYPTO=ON,-DENABLE_SUBTLE_CRYPTO=OFF,"
PACKAGECONFIG[video] = "-DENABLE_VIDEO=ON -DENABLE_VIDEO_TRACK=ON,-DENABLE_VIDEO=OFF -DENABLE_VIDEO_TRACK=OFF,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad,${RDEPS_VIDEO}"
PACKAGECONFIG[webaudio] = "-DENABLE_WEB_AUDIO=ON,-DENABLE_WEB_AUDIO=OFF,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good,${RDEPS_WEBAUDIO}"
PACKAGECONFIG[native_audio] = "-DENABLE_NATIVE_AUDIO=ON, -DENABLE_NATIVE_AUDIO=OFF,"

# DRM
# new CDMI option which uses WPEFramework
PACKAGECONFIG[wpeframework_opencdm] = "-DENABLE_OPENCDM=ON,-DENABLE_OPENCDM=OFF,wpeframework-clientlibraries,rdkservices"

PACKAGECONFIG[provisioning] = "-DENABLE_PROVISIONING=ON,-DENABLE_PROVISIONING=OFF,libprovision,libprovision"

RDEPENDS_${PN} += "\
  libatomic \
"

EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=Release \
    -DCMAKE_COLOR_MAKEFILE=OFF -DBUILD_SHARED_LIBS=ON -DPORT=WPE \
    -G Ninja \
"

EXTRA_OECMAKE_append = " -DUSE_LD_GOLD=OFF"

COMPATIBLE_MACHINE_mipsel = "(.*)"
LDFLAGS_append = " -Wl,--no-keep-memory"

# don't build debug
FULL_OPTIMIZATION_remove = "-g"
FULL_OPTIMIZATION_append = " -g1"
FULL_OPTIMIZATION_remove = "-Os"
FULL_OPTIMIZATION_remove = "-O2"
FULL_OPTIMIZATION_remove = "-O3"

WPE_WEBKIT_OPTIMIZATION ?= "-O2"

# Prevent a compile-time crash
SELECTED_OPTIMIZATION_remove = "-pipe"
SELECTED_OPTIMIZATION_append = " ${WPE_WEBKIT_OPTIMIZATION}"

LEAD_SONAME ?= "libWPEWebKit.so"

PACKAGES =+ "${PN}-web-inspector-plugin"

FILES_${PN}-web-inspector-plugin += "${libdir}/libWPEWebInspectorResources.so"
INSANE_SKIP_${PN}-web-inspector-plugin = "dev-so"
INSANE_SKIP_${PN}_append = " rpaths"

RDEPS_MEDIASOURCE = " \
    gstreamer1.0-plugins-good-isomp4 \
"

RDEPS_VIDEO = " \
    gstreamer1.0-plugins-base-app \
    gstreamer1.0-plugins-base-playback \
    gstreamer1.0-plugins-good-souphttpsrc \
"

RDEPS_WEBAUDIO = " \
    gstreamer1.0-plugins-good-wavparse \
"

# plugins-bad config option 'dash' -> gstreamer1.0-plugins-bad-dashdemux
# plugins-bad config option 'videoparsers' -> gstreamer1.0-plugins-bad-videoparsersbad

RDEPS_EXTRA = " \
    gstreamer1.0-plugins-base-audioconvert \
    gstreamer1.0-plugins-base-audioresample \
    gstreamer1.0-plugins-base-gio \
    gstreamer1.0-plugins-base-videoconvert \
    gstreamer1.0-plugins-base-videoscale \
    gstreamer1.0-plugins-base-volume \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-good-audiofx \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-good-autodetect \
    gstreamer1.0-plugins-good-avi \
    gstreamer1.0-plugins-good-deinterlace \
    gstreamer1.0-plugins-good-interleave \
    gstreamer1.0-plugins-bad-dashdemux \
    gstreamer1.0-plugins-bad-mpegtsdemux \
    gstreamer1.0-plugins-bad-smoothstreaming \
    gstreamer1.0-plugins-bad-videoparsersbad \
"

# The RDEPS_EXTRA plugins are all required for certain media playback use cases,
# but have not yet been classified as being specific dependencies for video,
# webaudio or mediasource. Until that classification is done, add them all to
# each of the three groups...

RDEPS_MEDIASOURCE += "${RDEPS_EXTRA}"
RDEPS_VIDEO += "${RDEPS_EXTRA}"
RDEPS_WEBAUDIO += "${RDEPS_EXTRA}"

RRECOMMENDS_${PN} += "ca-certificates"
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"
