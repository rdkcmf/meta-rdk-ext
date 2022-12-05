inherit features_check

REQUIRED_DISTRO_FEATURES = "wpe-2.28"

PATCHTOOL = "git"

require wpe-webkit.inc

PV .= "+git${SRCPV}"

DEPENDS_append = " libepoxy libgcrypt"
RDEPENDS_${PN} += "wpe-backend-rdk-platform-plugin"
RDEPENDS_${PN}_remove = "injectedbundle"

inherit gettext

# Tip of the branch on Dec 5, 2022
SRCREV = "1029e1f72fdb4ba765b39cbb7a27f41ac6d656b8"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;protocol=http;branch=wpe-2.28"

SRC_URI = "${BASE_URI}"

# Drop after a PR is approved or different fix is available in wpe-2.28 branch
SRC_URI += "file://2.28/868.patch"
SRC_URI += "file://2.28/914.patch"
SRC_URI += "file://2.28.2/issue-969.patch"
SRC_URI += "file://2.28/989.patch"
SRC_URI += "file://2.28.2/990.patch"
SRC_URI += "file://2.28.3/996.patch"
SRC_URI += "file://2.28/1009.patch"
SRC_URI += "file://2.28.3/1010.patch"

# Comcast specific changes
SRC_URI += "file://2.28/comcast-XRE-15382-libwebrtc-fake-encoder.patch"
SRC_URI += "file://2.28/comcast-WKIT-553-add-video-ave-mimetype-for-holepunch.patch"
SRC_URI += "file://2.28.1/comcast-XRE-13505-dynamic-insertion-of-decryptor-element.patch"
SRC_URI += "file://2.28/comcast-RDK-33188-gamepad-support.patch"
SRC_URI += "file://2.28.3/comcast-DELIA-24951-log-HTML5-video-playback.patch"
SRC_URI += "file://2.28/comcast-XRE-13593-EME-generate-MEDIA_ERR_ENCRYPTED-f.patch"
SRC_URI += "file://2.28/comcast-XRE-13851-Increase-html-parser-time-limit.patch"
SRC_URI += "file://2.28/comcast-AMLOGIC-628-Gstreamer-always-initialze-volume.patch"
SRC_URI += "file://2.28/comcast-RDKTV-1411-force-stop-media-on-loading-about.patch"
SRC_URI += "file://2.28/comcast-DELIA-48342-additional-logs-in-SlotVisitor.patch"
SRC_URI += "file://2.28/comcast-RDKTV-6665-Remove-screen-saver-disabler.patch"
SRC_URI += "file://2.28/comcast-LLAMA-2184-Support-for-external-sink-for-x-d.patch"
SRC_URI += "file://2.28/comcast-XRE-16393-DELIA-52142-Accessibility.patch"
SRC_URI += "file://2.28/comcast-XRE-14272-Speech-Synthesis.patch"
SRC_URI += "file://2.28/comcast-XRE-13799-XRE-13989-Track-encrypted-playback.patch"
SRC_URI += "file://2.28/comcast-RDKTV-380-disable-privileges-loss.patch"
SRC_URI += "file://2.28/comcast-RDK-28954-add-securedump-location.patch"
SRC_URI += "file://2.28/comcast-RDKTV-17737-play-pause-mapping.patch"
SRC_URI += "file://2.28/comcast-XRE-15382-XIONE-4595-RDKTV-17736-HDR-DV-conf.patch"
SRC_URI += "file://2.28/comcast-RDK-37080-disable-reenqueing-of-audio-sample.patch"
SRC_URI += "file://2.28/comcast-RDKTV-17281-RDKTV-17781-Workaround-for-AppleTV-rende.patch"
SRC_URI += "file://2.28/comcast-RDK-37379-Mute-release-logging.patch"
SRC_URI += "file://2.28/comcast-DELIA-57173-WebRTC-disable-VAD-by-default.patch"
SRC_URI += "file://2.28/comcast-RDKTV-18852-Restrict-inspection-of-locally-hosted-pages.patch"
SRC_URI += "file://2.28/comcast-RDK-38169-fix-build-for-Broadcom-platform.patch"
SRC_URI += "file://2.28.3/comcast-DELIA-57933-Increase-minor-version-or-WPE-lib.patch"
SRC_URI += "file://2.28.1/comcast-RDKTV-19578-Disable-webaudio-source-provider.patch"
SRC_URI += "file://2.28.1/comcast-LLAMA-8030-Fix-init-data-filtering.patch"
SRC_URI += "file://2.28/comcast-RDK-31407-RDK-38703-secondary-video-decoder-setup.patch"
SRC_URI += "file://2.28/comcast-LLAMA-8558-vttcue-middle-align-keyword-compa.patch"
SRC_URI += "file://2.28/comcast-LLAMA-7963-disable-handling-of-http-protocol.patch"
SRC_URI += "file://2.28/comcast-LLAMA-8893-Fix-audio-mute-when-using-asplayer.patch"
SRC_URI += "file://2.28/comcast-DELIA-59087-Disable-pausing-playback-for-buf.patch"

PACKAGECONFIG[westeros]          = "-DUSE_WPEWEBKIT_PLATFORM_WESTEROS=ON -DUSE_GSTREAMER_HOLEPUNCH=ON -DUSE_EXTERNAL_HOLEPUNCH=ON -DUSE_WESTEROS_SINK=ON,,westeros westeros-sink"
PACKAGECONFIG[encryptedmedia]    = "-DENABLE_ENCRYPTED_MEDIA=ON,-DENABLE_ENCRYPTED_MEDIA=OFF,"
PACKAGECONFIG[mathml]            = "-DENABLE_MATHML=ON,-DENABLE_MATHML=OFF,"
PACKAGECONFIG[touchevents]       = "-DENABLE_TOUCH_EVENTS=ON,-DENABLE_TOUCH_EVENTS=OFF,"
PACKAGECONFIG[meterelement]      = "-DENABLE_METER_ELEMENT=ON,-DENABLE_METER_ELEMENT=OFF,"
PACKAGECONFIG[svgfonts]          = "-DENABLE_SVG_FONTS=ON,-DENABLE_SVG_FONTS=OFF,"
PACKAGECONFIG[remoteinspector]   = "-DENABLE_REMOTE_INSPECTOR=ON,-DENABLE_REMOTE_INSPECTOR=OFF,"
PACKAGECONFIG[vp9]               = ",,,gstreamer1.0-plugins-good-matroska"
PACKAGECONFIG[vp9_hdr]           = "-DENABLE_HDR=ON,-DENABLE_HDR=OFF,,gstreamer1.0-plugins-good-matroska"
PACKAGECONFIG[gstreamergl]       = "-DUSE_GSTREAMER_GL=ON,-DUSE_GSTREAMER_GL=OFF,"
PACKAGECONFIG[mediastream]       = "-DENABLE_MEDIA_STREAM=ON -DENABLE_WEB_RTC=ON,-DENABLE_MEDIA_STREAM=OFF -DENABLE_WEB_RTC=OFF,libevent libopus libvpx alsa-lib,libevent"
PACKAGECONFIG[asan]              = "-DENABLE_SANITIZERS=address,,gcc-sanitizers"
PACKAGECONFIG[hevc]              = "-DENABLE_HEVC=ON,-DENABLE_HEVC=OFF,,"
PACKAGECONFIG[dolbyvision]       = "-DENABLE_DV=ON,-DENABLE_DV=OFF,,"
PACKAGECONFIG[developermode]     = "-DDEVELOPER_MODE=ON,-DDEVELOPER_MODE=OFF,"
PACKAGECONFIG[accessibility]     = "-DENABLE_ACCESSIBILITY=ON,-DENABLE_ACCESSIBILITY=OFF,atk tts rdkat,rdkat"
PACKAGECONFIG[speechsynthesis]   = "-DENABLE_SPEECH_SYNTHESIS=ON,-DENABLE_SPEECH_SYNTHESIS=OFF,tts"
PACKAGECONFIG[woff2]             = "-DUSE_WOFF2=ON,-DUSE_WOFF2=OFF,"
PACKAGECONFIG[openjpeg]          = "-DUSE_OPENJPEG=ON,-DUSE_OPENJPEG=OFF,"
PACKAGECONFIG[webcrypto]         = "-DENABLE_WEB_CRYPTO=ON,-DENABLE_WEB_CRYPTO=OFF,libtasn1"
PACKAGECONFIG[bubblewrapsandbox] = "-DENABLE_BUBBLEWRAP_SANDBOX=ON,-DENABLE_BUBBLEWRAP_SANDBOX=OFF,"
PACKAGECONFIG[webdriver]         = "-DENABLE_WEBDRIVER=ON,-DENABLE_WEBDRIVER=OFF,"
PACKAGECONFIG[serviceworker]     = "-DENABLE_SERVICE_WORKER=ON,-DENABLE_SERVICE_WORKER=OFF,"
PACKAGECONFIG[experimental]      = "-DENABLE_EXPERIMENTAL_FEATURES=ON,-DENABLE_EXPERIMENTAL_FEATURES=OFF,"
PACKAGECONFIG[wpeframework_opencdm] = "-DENABLE_THUNDER=ON,-DENABLE_THUNDER=OFF,wpeframework-clientlibraries,rdkservices"
PACKAGECONFIG[releaselog]        = "-DENABLE_RELEASE_LOG=ON,"
PACKAGECONFIG[usesoup2]          = "-DUSE_SOUP2=ON,-DUSE_SOUP2=OFF,libsoup-2.4"
PACKAGECONFIG[usesoup3]          = "-DUSE_SOUP2=OFF,,libsoup"
PACKAGECONFIG[native_audio]      = "-DUSE_GSTREAMER_NATIVE_AUDIO=ON, -DUSE_GSTREAMER_NATIVE_AUDIO=OFF,"
PACKAGECONFIG[native_video]      = "-DUSE_GSTREAMER_NATIVE_VIDEO=ON, -DUSE_GSTREAMER_NATIVE_VIDEO=OFF,"
PACKAGECONFIG[subtlecrypto]      = ""

PACKAGECONFIG_append = " webcrypto webdriver intl remoteinspector experimental releaselog accessibility speechsynthesis native_video"
PACKAGECONFIG_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'enable_libsoup3', 'usesoup3', 'usesoup2', d)}"

FILES_${PN} += " ${libdir}/wpe-webkit-1.0/injected-bundle/libWPEInjectedBundle.so"
FILES_${PN}-web-inspector-plugin += " ${libdir}/wpe-webkit-1.0/libWPEWebInspectorResources.so"

SELECTED_OPTIMIZATION_remove = "-g"
SELECTED_OPTIMIZATION_append = " -g1 "

TUNE_CCARGS_remove = "-fno-omit-frame-pointer -fno-optimize-sibling-calls"
TUNE_CCARGS_append = " -fno-delete-null-pointer-checks"

RDEPS_VIDEO += " \
    gstreamer1.0-plugins-bad-opusparse \
"

def wk_use_ccache(bb,d):
    if d.getVar('CCACHE_DISABLED') == "1":
       return "NO"
    if bb.data.inherits_class("icecc", d) and d.getVar('ICECC_DISABLED') != "1":
       return "NO"
    return "YES"
export WK_USE_CCACHE="${@wk_use_ccache(bb, d)}"

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'enable_wpe-webdriver', 'false', 'true', d)}; then
        rm ${D}/usr/bin/WPEWebDriver
    fi
}
