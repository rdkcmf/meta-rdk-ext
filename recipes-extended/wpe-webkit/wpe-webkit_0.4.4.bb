require wpe-webkit.inc

PV = "0.4.4+git${SRCPV}"

DEPENDS_append = " wpe-backend atk tts rdkat libgcrypt rsync-native"
RDEPENDS_${PN} += "wpe-backend-rdk-platform-plugin tts rdkat"
DEPENDS_remove_daisy = " rsync-native"

# revision date: Wed Oct 16 13:13:39 2019 +0200
SRCREV = "5f899bc2e0e3736908d46ddf473dc4fe5c7f5c95"

BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;protocol=http;branch=wpe-20170728"

SRC_URI = "${BASE_URI}"

SRC_URI += "file://0001-Revert-WPE-Use-libepoxy_0.4.1.patch"
SRC_URI += "file://0001-wpe-0.4-configuration-fixes_0.4.1.patch"
SRC_URI += "file://0002-comcast-AVEHolePuncher_0.4.1.patch"
SRC_URI += "file://0005-comcast-enable-wpelauncher_0.4.patch"
SRC_URI += "file://0007-comcast-Always-honor-tfdt-for-older-GStreamer-versions_0.4.patch"
SRC_URI += "file://0008-comcast-set-local-storage-quota-to-100K_0.4.patch"
SRC_URI += "file://0009-comcast-broadcom-webaudio-workaround_0.4.1.patch"
SRC_URI += "file://0010-comcast-broadcom-audio-eos-workaround_0.4.1.patch"
SRC_URI += "file://0011-comcast-allow-back-navigation-on-backspace-hack_0.4.patch"
SRC_URI += "file://0014-comcast-browse-to-watch-log_0.4.patch"
SRC_URI += "file://0016-comcast-DelayedAudio-test_0.4.patch"
SRC_URI += "file://0017-comcast-Revert-Change-antialiasing-policy-on-the-accelerated_0.4.1.patch"
SRC_URI += "file://0023-comcast-Youtube-improvements_0.4.1.patch"
#SRC_URI += "file://0025-MSE-EME-GStreamer-fix-0-totalEncryptedBytes_0.4.1.patch"
SRC_URI += "file://0026-comcast-downscale-jpeg-images_0.4.patch"
SRC_URI += "file://0028-comcast-detect-when-process-become-responsive_0.4.patch"
SRC_URI += "file://0030-Adding-an-API-to-configure-MediaCacheDirectory-in-WK_0.4.patch"
SRC_URI += "file://0033-comcast-prevent-localhost-pages-in-remote-webinspector_0.4.patch"
SRC_URI += "file://0035-comcast-indexeddb-support_0.4.patch"
SRC_URI += "file://0036-comcast-EME-add-SVP-support_0.4.2.patch"
SRC_URI += "file://0040-Live-Content-Duration_0.4.1.patch"
SRC_URI += "file://0041-MemoryPressureHandlerLinux-to-take-into-account-memo_0.4.patch"
SRC_URI += "file://0044-comcast-increase-default-audio-video-buffer-for-4k_0.4.patch"
SRC_URI += "file://0047-comcast-initial-support-for-playback-at-different-rates_0.4.1.patch"


SRC_URI += "file://0048-Use-input-gain-to-configure-audio-volume-on-Intel-bo_0.4.2.patch"
SRC_URI += "file://0049-comcast-add-EAC3-support_0.4.patch"
SRC_URI += "file://0050-comcast-Prioritize-ping-web-process-messages_0.4.patch"
SRC_URI += "file://0051-comcast-Internet-Audio-live-streaming-issues-fixed_0.4.patch"
SRC_URI += "file://0052-comcast-Gap-addjustment-based-on-first-pts_0.4.patch"
SRC_URI += "file://0054-comcast-Add-an-option-to-stop-start-memory-pressure-handler_0.4.patch"
SRC_URI += "file://0056-comcast-Youtube-progress-logs_0.4.patch"
SRC_URI += "file://0057-web-inspector-ui-fixes_0.4.patch"
SRC_URI += "file://0058-comcast-VP9-support_0.4.3.patch"
SRC_URI += "file://0062-comcast-webRTC-integration_0.4.1.patch"
SRC_URI += "file://0063-comcast-wpe-0.4.2-compilation-fix.patch"
SRC_URI += "file://0064-wpe-accessibility_0.4.3.patch"
SRC_URI += "file://0065-wpe-media-volume-control-for-tts_0.4.patch"
SRC_URI += "file://0069-MSE-Move-SourceBuffer-s-pending-append-data-into-the_0.4.patch"

SRC_URI += "file://0071-update-supportsType-mimetypes_0.4.3.patch"
SRC_URI += "file://0072-Adding-Graphics-Memory-percentage-in-System-Resource_0.4.patch"
SRC_URI += "file://0074-Defer-seek-complition-until-it-is-actually-finished_0.4.patch"
SRC_URI += "file://0077-Fix-use-after-free-error.patch"
SRC_URI += "file://0078-Fix-global-buffer-overflow-in-RenderThemeWPE-mediaCo.patch"
SRC_URI += "file://0079-comcast-gcrypt-changes-for-intel.patch"
SRC_URI += "file://0082-GStreamer-MSE-don-t-try-to-advacne-ready-state-on-pl.patch"
SRC_URI += "file://0085-Enable-HEVC-support-in-OpenCDM-decryptor_0.4.3.patch"
#
SRC_URI += "file://0090-fixes-for-Youtube-encrypted-playback_0.4.4.patch"
SRC_URI += "file://0091-add-EAC3-OpenCDMDecryptorCaps_0.4.3.patch"
SRC_URI += "file://0092-Initial-support-for-SQLite-Encryption-Extension.patch"
SRC_URI += "file://0094-Don-t-send-caps-out-of-flow-in-already-configured-st.patch"
SRC_URI += "file://0095-Dynamic-insertion-of-decryptor-element_0.4.1.patch"
SRC_URI += "file://0096-MSE-Report-ended-after-seek.patch"
SRC_URI += "file://0099-comcast-Amazon-Prime-improvements_0.4.4.patch"
SRC_URI += "file://0101-comcast-dont-pause-rendering-on-visible-false-rdkbrowser2-reset.patch"
SRC_URI += "file://0102-comcast-Amazon-macroblocking-fix.patch"
SRC_URI += "file://0103-comcast-EME-generate-MEDIA_ERR_ENCRYPTED-for-decrypt_0.4.3.patch"
SRC_URI += "file://0107-comcast-EME-add-playbackStopped_0.4.4.patch"
#
SRC_URI += "file://0109-Fix-readyState-update-while-seeking-and-reporting-of.patch"
SRC_URI += "file://0110-Reduce-XHR-latency.patch"
SRC_URI += "file://0111-MIPS-backport-cacheFlush-fixes_0.4.1.patch"
SRC_URI += "file://0112-play-pause-mapping_0.4.1.patch"
SRC_URI += "file://0113-amazon-video-fix-continue-without-parser.patch"
SRC_URI += "file://0113-Access-ScalableImageDecoder-with-mutex_0.4.1.patch"
SRC_URI += "file://0114-network-process-unset-custom-breakpad-guid.patch"
SRC_URI += "file://0114-EME-enable-Widevine-with-env-variable_0.4.4.patch"
SRC_URI += "file://0115-comcast-Use-UPP_C-plane-for-video-playback-on-Intel_0.4.patch"
SRC_URI += "file://0115-Move-breakpad-to-webkit_0.4.1.patch"
SRC_URI += "file://0116-Make-IntelCE-behave-similar-to-Broadcom-devices.patch"
SRC_URI += "file://0116-Shared-JSC-build_0.4.1.patch"
#
SRC_URI += "file://0118-Reduce-the-impact-of-dev-logs.patch"
SRC_URI += "file://0119-Fix-crash-due-to-infinite-recursion-on-seek.patch"
SRC_URI += "file://0120-Revert-WPE-SoupNetworkSession.patch"
SRC_URI += "file://0120-Fix-dispatching-of-progress-events-in-paused-state.patch"
SRC_URI += "file://0121-Fix-EOS-message-delivery-on-Broadcom-devices_0.4.1.patch"
SRC_URI += "file://0122-Increase-parser-time-limit.patch"
#
SRC_URI += "file://0124-Fix-holepunching-after-re-parenting-video-layer_0.4.1.patch"
SRC_URI += "file://0125-WKIT-1148-Restoring-the-accessibility-enable-setting.patch"
SRC_URI += "file://0126-Start-buffer-append-after-dispatching-scheduled-even.patch"

#SRC_URI += "file://0128-Fix-crash-on-attempt-to-decrypt-with-null-m_openCdm_0.4.2.patch"

SRC_URI += "file://0130-Track-encrypted-content-playback-0.4.1.patch"
SRC_URI += "file://0131-Revert-GTK-WPE-Improve-the-way-request-displayRefresh_0.4.1.patch"
SRC_URI += "file://0134-Reset-m_isEndReached-on-unmarkEndOfStream_0.4.1.patch"
SRC_URI += "file://0126-Dynamic-change-to-NonCompositedWebGL_0.4.1.patch"
SRC_URI += "file://0135-Prefer-webkit-decryptors_0.4.1.patch"
SRC_URI += "file://0138-MSE-Fix-empty-appends-and-make-abort-more-consistent_0.4.1.patch"
SRC_URI += "file://0139-Implement-hasPendingActivity-for-MediaKeySession_0.4.1.patch"
SRC_URI += "file://0141-Increase-clear-SB-limits-for-4K-devices_0.4.1.patch"
SRC_URI += "file://0142-Time-encrypted-content-playback-0.4.2.patch"
SRC_URI += "file://0142-Add-API-to-explicitly-request-memory-release.patch"
SRC_URI += "file://0143-Removing-setPosion-which-causes-flickering-in-youtube_0.4.2.patch"
SRC_URI += "file://0144-Disable-memory-pressure-relief-logging.patch"
SRC_URI += "file://0145-Improve-memory-pressure-handler.patch"
SRC_URI += "file://0146-Report-RSS-as-web-page-memory.patch"
SRC_URI += "file://0148-MSE-SourceBuffer-append-start-parsing-as-soon-as-pos.patch"
SRC_URI += "file://0149-Propagate-parsing-errors-to-MSE-SourceBuffer.patch"
SRC_URI += "file://0150-Handle-gst-pad-error-as-format-error.patch"
SRC_URI += "file://0151-Detect-DolbyVision.patch"
# SRC_URI += "file://0152-Disable-Widevine-for-Youtube_0.4.2.patch"
SRC_URI += "file://0153-Pause-media-and-skip-compositor-scene-rendering-hide.patch"
SRC_URI += "file://0154-Tail-calls-are-broken-on-ARM_THUMB2-and-MIPS.patch"
SRC_URI += "file://0155-Fast-exit-for-WebKit-processes.patch"
SRC_URI += "file://0156-Speech-Synthesis_0.4.3.patch"
SRC_URI += "file://0157-accessibility-control-for-tts_0.4.2.patch"


SRC_URI += "file://0158-Fix-crash-on-dynamic-change-from-NonCompositedWebGL_0.4.2.patch"
SRC_URI += "file://0159-MSE-update-m_bufferFull-flag-when-evicted-enough.patch"
SRC_URI += "file://0160-Backport-MSE-timestampOffset-can-introduce-floating.patch"
SRC_URI += "file://0161-MSE-SourceBuffer-Fix-creating-float-PTS-DTS-when-dividing-sample.patch"
SRC_URI += "file://0162-MSE-SourceBuffer-Fix-removal-of-paddings-from-buffer.patch"
SRC_URI += "file://0163-Backport-MSE-Frame-re-ordering-can-cause-iframes-to-.patch"
SRC_URI += "file://0164-Backport-Safari-is-not-able-to-adapt-between-H264-st.patch"
SRC_URI += "file://0165-MSE-make-lastEnqueuedPresentationTime-be-highest-enq.patch"
SRC_URI += "file://0166-Fix-build-of-WebCoreTestSupport.patch"
SRC_URI += "file://0149-connecting-webdriver-to-rdkbrower-for-application-test.patch"
SRC_URI += "file://0167-Disable-async-image-decoding.patch"
SRC_URI += "file://0168-comcast-Add-API-to-set-IngoreResize-in-WPEView.patch"
SRC_URI += "file://0169-comcast-Fix-dispatching-of-encrypted-event.patch"
SRC_URI += "file://0170-Update-video-rectangle-only-on-video-sink-message.diff"
SRC_URI += "file://0171-Add-isEmpty-API-to-PODRedBlackTree.patch"
SRC_URI += "file://0172-Make-SourceBuffer-evictCodedFrames-less-aggressive.patch"
SRC_URI += "file://0173-Backport-fix-for-crash-in-Inspector-AsyncStackTrace-.patch"
SRC_URI += "file://0174-XRE-14411-RDK-AT-handles-all-types-of-headers.patch"
SRC_URI += "file://0175-Make-an-attempt-to-start-decryption-when-attaching_0.4.4.patch"
SRC_URI += "file://0176-Enable-boxed-video-zoom-mode-for-westerossink-on-Broa.patch"
SRC_URI += "file://0177-Fix-occasional-HDR-video-playback-stalls-after-seek.patch"
SRC_URI += "file://0178-XRE-14630-App-throwing-Error-when-an-error-occurred.patch"
SRC_URI += "file://0179-Make-sure-updatestart-is-fired-even-if-listener-adde.patch"
SRC_URI += "file://0180-Add-MediaError.message-and-clarify-decryption-errors_0.4.4.patch"
SRC_URI += "file://0181-Fixing-memory-leak-issues-in-sample-buffer.patch"
SRC_URI += "file://0183-DFG-s-StrengthReduction-phase-should-not-reduce-Cons.patch"
SRC_URI += "file://0184-XRE-14631-Fixes-for-RWI-stops-working-randomly.patch"
SRC_URI += "file://0184-fix-for-DAZN-streams.patch"
SRC_URI += "file://0185-Fix-crash-in-compositing-run-loop.patch"
SRC_URI += "file://0186-Fix-build-of-wpe-test-bundle.patch"
SRC_URI += "file://0187-XRE-15546-RDM-RWI-WI-starts-working-only-after-resta.patch"
SRC_URI += "file://0188-Introduce-WKNavigationResponseCopyURLResponse.patch"
SRC_URI += "file://0189-Validate-allowScriptsToCloseWindows-exclusively.patch"
SRC_URI += "file://0190-Make-CDMInstance-ref-counter-thread-safe.patch"
SRC_URI += "file://0001-dom-fix-build-issue-from-gcc-9.2.patch"
SRC_URI += "file://0191-Update-CDMInstance-on-attempt-to-decrypt.patch"
SRC_URI += "file://0192-405564d67c5bcbb187be2447eea84df24cc44a94.patch"
SRC_URI += "file://0193-Port-TTS-plugin-changes-to-wpe-0.4.4.patch"
SRC_URI += "file://add-securedump-location.patch"
SRC_URI += "file://0192-RWI-works-for-one-app-session-then-fail.patch"
SRC_URI += "file://0194-Second-ESPN-App-launch-after-Live-playback-fails.patch"
SRC_URI += "file://0195-update-the-sample-durtion-based-on-the-delta-of-last.patch"
SRC_URI += "file://0196-refactor-fetch-body.patch"

# device specific configs
PACKAGECONFIG[intelce] = "-DUSE_WPEWEBKIT_BACKEND_INTEL_CE=ON -DUSE_WPEWEBKIT_PLATFORM_INTEL_CE=ON -DUSE_HOLE_PUNCH_GSTREAMER=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,intelce-display"
PACKAGECONFIG[nexus] = "-DUSE_WPEWEBKIT_BACKEND_BCM_NEXUS=ON -DUSE_WPEWEBKIT_PLATFORM_BCM_NEXUS=ON -DUSE_HOLE_PUNCH_GSTREAMER=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,broadcom-refsw"
PACKAGECONFIG[rpi] = "-DUSE_WPEWEBKIT_BACKEND_BCM_RPI=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=ON,,userland"
PACKAGECONFIG[wayland] = "-DUSE_WPEWEBKIT_BACKEND_WAYLAND=ON -DUSE_WPE_BUFFER_MANAGEMENT_BCM_RPI=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF,,wayland libxkbcommon"
PACKAGECONFIG[westeros] = "-DUSE_WPEWEBKIT_BACKEND_WESTEROS=ON -DUSE_WPEWEBKIT_PLATFORM_WESTEROS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_HOLE_PUNCH_GSTREAMER=ON -DUSE_HOLE_PUNCH_EXTERNAL=ON -DUSE_WESTEROS_SINK=ON,,wayland westeros libxkbcommon"
PACKAGECONFIG[encryptedmedia] = "-DENABLE_ENCRYPTED_MEDIA=ON,-DENABLE_ENCRYPTED_MEDIA=OFF,"

PACKAGECONFIG[mathml] = "-DENABLE_MATHML=ON,-DENABLE_MATHML=OFF,"
PACKAGECONFIG[touchevents] = "-DENABLE_TOUCH_EVENTS=ON,-DENABLE_TOUCH_EVENTS=OFF,"
PACKAGECONFIG[meterelement] = "-DENABLE_METER_ELEMENT=ON,-DENABLE_METER_ELEMENT=OFF,"
PACKAGECONFIG[svgfonts] = "-DENABLE_SVG_FONTS=ON,-DENABLE_SVG_FONTS=OFF,"
PACKAGECONFIG[allinone] = "-DENABLE_ALLINONE_BUILD=ON,-DENABLE_ALLINONE_BUILD=OFF,"
PACKAGECONFIG[vp9] = ",,,gstreamer1.0-plugins-good-matroska"
PACKAGECONFIG[vp9_hdr] = "-DENABLE_VP9_HDR=ON,-DENABLE_VP9_HDR=OFF,,gstreamer1.0-plugins-good-matroska"
PACKAGECONFIG[encryptedlocalstorage] = "-DENABLE_SQLITE_ENCRYPTION_EXTENSION=ON,-DENABLE_SQLITE_ENCRYPTION_EXTENSION=OFF,sqlite3see"
PACKAGECONFIG[shared_jsc] = "-DENABLE_SHARED_JSC=ON,,"

PACKAGECONFIG_append = " allinone intl"

SELECTED_OPTIMIZATION_remove = "-g"
SELECTED_OPTIMIZATION_append = " -g1 "

TUNE_CCARGS_remove = "-fno-omit-frame-pointer -fno-optimize-sibling-calls"

EXTRA_OECMAKE += " \
    -DEXPORT_DEPRECATED_WEBKIT2_C_API=ON \
    -DENABLE_TOOLS=OFF \
"

do_compile[progress] = "percent"
do_compile() {
    export NINJA_STATUS='%p '
    ${STAGING_BINDIR_NATIVE}/ninja ${PARALLEL_MAKE} libWPEWebKit.so libWPEWebInspectorResources.so WPEWebProcess WPENetworkProcess WPEStorageProcess WPEWebDriver
}

do_install() {
    bbnote DESTDIR='${D}' cmake --build '${B}' --target install -- ${EXTRA_OECMAKE_BUILD}
    DESTDIR='${D}' cmake --build '${B}' --target install -- ${EXTRA_OECMAKE_BUILD}

    # Hack: Remove the RPATH embedded in libWPEWebKit.so
    chrpath --delete ${D}${libdir}/libWPEWebKit.so
    # Hack: Remove RPATHs embedded in apps
    chrpath --delete ${D}${bindir}/WPEWebProcess
    chrpath --delete ${D}${bindir}/WPENetworkProcess
    chrpath --delete ${D}${bindir}/WPEStorageProcess
    chrpath --delete ${D}${bindir}/WPEWebDriver

    if ${@bb.utils.contains('DISTRO_FEATURES', 'enable_wpe-webdriver', 'false', 'true', d)}; then
        rm ${D}/usr/bin/WPEWebDriver
    fi

    #install header files
    mkdir -p ${D}${prefix}/include/WebKit/
    cp -ar ${B}/DerivedSources/ForwardingHeaders/WebKit ${D}${prefix}/include

    mkdir -p ${D}${prefix}/include/WebKit/Shared/API
    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/WebKit/Shared/API ${D}${prefix}/include/WebKit/Shared/

    mkdir -p ${D}${prefix}/include/WebKit/UIProcess/API
    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/WebKit/UIProcess/API ${D}${prefix}/include/WebKit/UIProcess/

    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/WTF/wtf ${D}${prefix}/include/

    mkdir -p ${D}${prefix}/include/JavaScriptCore/
    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/JavaScriptCore/API/ ${D}${prefix}/include/JavaScriptCore/

    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/WebKit/WebProcess/InjectedBundle/API/c/ ${D}${prefix}/include/WebKit/

    # rdkbrowser2 still uses WebKit2 as the prefix
    mkdir -p ${D}${prefix}/include/WebKit2/
    cp -ar  ${D}${prefix}/include/WebKit/*  ${D}${prefix}/include/WebKit2/

    # install pkg-config files
    install -d ${D}${libdir}/pkgconfig/
    cp -av --no-preserve=ownership ${B}/wpe-webkit.pc ${D}${libdir}/pkgconfig/

    if [ -e ${B}/lib/libWPEJavaScriptCore.so ]; then
        cp -av --no-preserve=ownership ${B}/lib/libWPEJavaScriptCore.so* ${D}${libdir}/
        chrpath --delete ${D}${libdir}/libWPEJavaScriptCore.so
        cp -av --no-preserve=ownership ${B}/Source/JavaScriptCore/wpe-javascriptcore.pc ${D}${libdir}/pkgconfig/
    fi
}
