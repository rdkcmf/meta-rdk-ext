require wpe-webkit.inc

PV = "2.22.3+git${SRCPV}"

DEPENDS_append = " wpe-backend atk tts libepoxy libgcrypt rdkat rsync-native"
RDEPENDS_${PN} += "wpe-backend-rdk-platform-plugin tts rdkat"
DEPENDS_remove_daisy = " rsync-native"

inherit gettext

#Commits on Nov 3, 2021
SRCREV = "a79319e92cb9d3ee958f2bf9e77724514d034b1c"
BASE_URI ?= "git://github.com/WebPlatformForEmbedded/WPEWebKit.git;protocol=http;branch=wpe-2.22"

SRC_URI = "${BASE_URI}"

SRC_URI += "file://2.22/0004-comcast-AVEHolePuncher.patch"
SRC_URI += "file://2.22/0006-Adding-an-API-to-configure-MediaCacheDirectory-in-WK.patch"
SRC_URI += "file://2.22/0007-comcast-broadcom-webaudio-workaround.patch"
SRC_URI += "file://2.22/0009-comcast-allow-back-navigation-on-backspace-hack.patch"
SRC_URI += "file://2.22/0011-comcast-browse-to-watch-log.patch"
SRC_URI += "file://2.22/0013-comcast-Revert-Change-antialiasing-policy-on-the-accelerated.patch"
SRC_URI += "file://2.22.3/0014-Comcast-Youtube-improvements.patch"
SRC_URI += "file://2.22/0016-comcast-detect-when-process-become-responsive.patch"
SRC_URI += "file://2.22/0017-comcast-prevent-localhost-pages-in-remote-webinspector.patch"
#SRC_URI += "file://2.22/0018-comcast-indexeddb-support.patch"
SRC_URI += "file://2.22.3/0019-comcast-EME-add-SVP-support.patch"
SRC_URI += "file://2.22/0020-Live-Content-Duration.patch"
SRC_URI += "file://2.22/0021-comcast-increase-default-audio-video-buffer-for-4k.patch"
SRC_URI += "file://2.22/0022-comcast-trigger-gc-to-release-players.patch"
SRC_URI += "file://2.22.3/0024-comcast-add-EAC3-support.patch"
SRC_URI += "file://2.22/0027-comcast-Gap-addjustment-based-on-first-pts.patch"
SRC_URI += "file://2.22.2/0028-comcast-youtube-progress-logs.patch"
SRC_URI += "file://2.22/0029-web-inspector-ui-fixes.patch"
SRC_URI += "file://2.22/0030-comcast-VP9-support.patch"
SRC_URI += "file://2.22/0032-update-supportsType-mimetypes.patch"
SRC_URI += "file://2.22/0033-Adding-Graphics-Memory-percentage-in-System-Resource.patch"
SRC_URI += "file://2.22/0035-Fix-use-after-free-error.patch"
SRC_URI += "file://2.22/0036-Enable-HEVC-support-in-OpenCDM-decryptor.patch"
SRC_URI += "file://2.22.1/0037-fixes-for-Youtube-encrypted-playback.patch"
SRC_URI += "file://2.22/0038-add-EAC3-OpenCDMDecryptorCaps.patch"
#SRC_URI += "file://2.22/0039-Initial-support-for-SQLite-Encryption-Extension.patch"
SRC_URI += "file://2.22/0041-Dynamic-insertion-of-decryptor-element.patch"
SRC_URI += "file://2.22/0042-MSE-Report-ended-after-seek.patch"
SRC_URI += "file://2.22/0045-comcast-Amazon-Prime-improvements.patch"
SRC_URI += "file://2.22/0046-comcast-dont-pause-rendering-on-visible-false-rdkbrowser2-reset.patch"
SRC_URI += "file://2.22/0047-comcast-EME-generate-MEDIA_ERR_ENCRYPTED-for-decryption.patch"
SRC_URI += "file://2.22/0048-comcast-EME-add-playbackStopped.patch"
SRC_URI += "file://2.22/0049-Fix-readyState-update-while-seeking-and-reporting-of-buffered.patch"
SRC_URI += "file://2.22/0050-Reduce-XHR-latency.patch"
SRC_URI += "file://2.22/0051-play-pause-mapping.patch"
SRC_URI += "file://2.22/0052-network-process-unset-custom-breakpad-guid.patch"
SRC_URI += "file://2.22/0057-Reduce-the-impact-of-dev-logs.patch"
SRC_URI += "file://2.22/0059-Revert-WPE-SoupNetworkSession.patch"
SRC_URI += "file://2.22.2/0060-Fix-dispatching-of-progress-events-in-paused-state.patch"
SRC_URI += "file://2.22/0061-Increase-parser-time-limit.patch"
SRC_URI += "file://2.22/0065-Start-buffer-append-after-dispatching-scheduled-events.patch"
SRC_URI += "file://2.22/0067-Track-encrypted-content-playback.patch"
SRC_URI += "file://2.22/0070-Dynamic-change-to-NonCompositedWebGL.patch"
SRC_URI += "file://2.22/0071-Prefer-webkit-decryptors.patch"
SRC_URI += "file://2.22/0072-MSE-Fix-empty-appends-and-make-abort-more-consistent.patch"
SRC_URI += "file://2.22.2/0074-Increase-clear-SB-limits-for-4K-devices.patch"
SRC_URI += "file://2.22/0075-Time-encrypted-content-playback.patch"
SRC_URI += "file://2.22/0076-Add-API-to-explicitly-request-memory-release.patch"
SRC_URI += "file://2.22/0077-Disable-memory-pressure-relief-logging.patch"
SRC_URI += "file://2.22/0079-vpx-and-opus-dependency-in-webrtc.patch"
SRC_URI += "file://2.22/0080-Report-RSS-as-web-page-memory.patch"
SRC_URI += "file://2.22/0082-XRE-14693-Fixed-Relay-candidate-resolving-on-libwebr.patch"

SRC_URI += "file://2.22/0083-MSE-SourceBuffer-append-start-parsing-as-soon-as-pos.patch"
SRC_URI += "file://2.22/0085-Detect-DolbyVision.patch"
SRC_URI += "file://2.22/0088-MSE-make-lastEnqueuedPresentationTime-be-highest-enq.patch"
SRC_URI += "file://2.22/0089-Comcast-Add-API-to-set-IngoreResize-in-WPEView.patch"
SRC_URI += "file://2.22/0092-Make-an-attempt-to-start-decryption-when-attaching-C.patch"
SRC_URI += "file://2.22/0093-Fast-exit-for-WebKit-processes.patch"
SRC_URI += "file://2.22/0095-Make-sure-updatestart-is-fired-even-if-listener-adde.patch"
SRC_URI += "file://2.22/0096-Fixing-memory-leak-issues-in-sample-buffer.patch"
SRC_URI += "file://2.22.2/0097-Add-MediaError.message-and-clarify-decryption-errors.patch"
# SRC_URI += "file://2.22/0098-skip-compositor-scene-rendering-hide.patch"
SRC_URI += "file://2.22.3/0102-Fix-crash-on-dynamic-change-from-NonCompositedWebGL.patch"
SRC_URI += "file://2.22/0103-Fix-crash-in-compositing-run-loop.patch"
SRC_URI += "file://2.22/0104-comcast-Add-an-option-to-stop-start-memory-pressure-handler.patch"
SRC_URI += "file://2.22/0105-Use-input-gain-to-configure-audio-volume.patch"
SRC_URI += "file://2.22/0149-connecting-webdriver-to-rdkbrower-for-applicati.patch"
SRC_URI += "file://2.22/0174-XRE-14411-RDK-AT-handles-all-types-of-headers.patch"
SRC_URI += "file://2.22/0184-XRE-14631-Fixes-for-RWI-stops-working-randomly.patch"
SRC_URI += "file://2.22/0185-removed-video-webm-audio-webm-from-mimeTypes.patch"
SRC_URI += "file://2.22/0187-XRE-15546-RDM-RWI-WI-starts-working-only-after-resta.patch"
SRC_URI += "file://2.22/0188-fixed-build-error-when-disabled-WEB_AUDIO-and-enabled_MEDIA_STREAM.patch"
SRC_URI += "file://2.22/0189-fix-for-DAZN-streams.patch"
SRC_URI += "file://2.22/0190-Make-CDMInstance-ref-counter-thread-safe.patch"
SRC_URI += "file://2.22/0191-Gamepad-for-WPE.patch"

SRC_URI += "file://2.22/0198-Hack-keep-extrapolating-pts-to-get-game-stream-worki.patch"
SRC_URI += "file://2.22.2/0199-webrtc-improvements-for-xcloudgames.patch"
SRC_URI += "file://2.22/0200-Validate-allowScriptsToCloseWindows-exclusively.patch"
SRC_URI += "file://2.22/0203-MSE-disable-optional-parsers-by-default.patch"
SRC_URI += "file://2.22/0204-GStreamer-MSE-Actually-implement-flush-on-playback-p.patch"
SRC_URI += "file://2.22/0206-GStreamer-always-initialze-volume.patch"
SRC_URI += "file://2.22/0210-Fix-crash-on-dynamic-change-of-non-composited-webgl-.patch"
SRC_URI += "file://2.22/add-securedump-location.patch"

SRC_URI += "file://2.22/0201-fixed-build-error-on-JSC.patch"
SRC_URI += "file://2.22/0204-included-linux-sockios.h-to-fix-a-build-error.patch"
SRC_URI += "file://2.22/0212-Fix-issue-that-webkit-cannot-render-picture-on-westeros-backend.patch"
SRC_URI += "file://2.22.3/0216-Accessibility.patch"
SRC_URI += "file://2.22/0217-Speech-Synthesis.patch"
SRC_URI += "file://2.22/0218-Invalidate-3D-layer-proxy-on-destruction.patch"
SRC_URI += "file://2.22/0218-Update-CDMInstance-on-attempt-to-decrypt.patch"
SRC_URI += "file://2.22/0219-Workaround-initial-seek-failures.patch"
SRC_URI += "file://2.22/0220-Allow-independed-flushing-of-video-buffer.patch"
SRC_URI += "file://2.22/0221-comcast-force-stop-media-on-loading-about-blank.patch"
SRC_URI += "file://2.22/0222-Fix-sprite-rendering-issue.patch"
SRC_URI += "file://2.22.1/0221-comcast-fix-use-after-free-on-dynamic-switch-from-non-compos.patch"
SRC_URI += "file://2.22/0223-Fix-crash-in-setRootCompositingLayer-on-page-close.patch"
SRC_URI += "file://2.22.2/0224-Fix-ASAN-build.patch"
SRC_URI += "file://2.22.3/0225-Revert-GStreamer-EME-Lock-while-CDM-updates-keys.patch"
SRC_URI += "file://2.22.1/0226-Use-process-status-to-compute-memory-footprint.patch"
SRC_URI += "file://2.22/0228-Enforce-normal-layer-composition-policy.patch"
SRC_URI += "file://2.22/0229-Disable-gst-fast-alloc-by-default.patch"
SRC_URI += "file://2.22/0234-Fix-reporting-of-extraMemory-cost-of-removed-sourceb.patch"
SRC_URI += "file://2.22/0235-Enable-cgroup-memory-measurements.patch"
SRC_URI += "file://2.22/0236-Fix-RWI-on-macOS-Big-Sur.patch"
SRC_URI += "file://2.22/0235-Skip-forward-discontinuity-detection-by-audio-decode.patch"
SRC_URI += "file://2.22/0237-Use-widget-bounds-for-platform-screenRect.patch"
SRC_URI += "file://2.22/0238-Prevent-localStorage-from-growing-indefinitely.patch"
SRC_URI += "file://2.22/0239-Setting-SW-Decoder-property-to-westerossink.patch"
SRC_URI += "file://2.22/0240-Keysystem-change-for-Fandango.patch"
SRC_URI += "file://2.22/0242-MSE-avoid-unneeded-flushing-of-video-source-buffer.patch"
SRC_URI += "file://2.22/0243-JSC-Make-ConcurrentJSLock-Lock-even-if-ENABLE_CONCUR.patch"
SRC_URI += "file://2.22/0271-GStreamer-try-to-detect-and-recover-inconsistent-pla.patch"
SRC_URI += "file://2.22/0272-Fix-media-playback-looping.patch"
SRC_URI += "file://2.22/0272-Increase-fudge-factor-to-3-frames.patch"
SRC_URI += "file://2.22.2/0001-Add-support-to-set-media-type-in-OCDM-CDM.patch"


SRC_URI += "file://2.22.2/0275-Fix-use-after-free-in-GstMappedBuffer-destructor.patch"
SRC_URI += "file://2.22.2/0276-Remove-screen-saver-disabler.patch"
SRC_URI += "file://2.22.2/0277-Delete-corrupted-localstorage-db.patch"
SRC_URI += "file://2.22.2/0274-added-SVP-transform-for-clear-and-encrypted-samples.patch"
SRC_URI += "file://2.22/0278-RWI-works-for-one-app-session-then-fail.patch"
SRC_URI += "file://2.22.2/0278-Add-option-to-enable-dolby-vision-in-codec-set.patch"
SRC_URI += "file://2.22.2/0278-Support-for-external-sink-for-x-dvb.patch"
SRC_URI += "file://2.22/0279-Second-ESPN-App-launch-after-Live-playback-fails.patch"

#SRC_URI += "file://2.22/0282-Reduce-live-audio-only-buffering-iHeart.patch"
SRC_URI += "file://2.22/0284-Reduce-queue2-high-watermark-to-speed-up-iheart-playback.patch"

SRC_URI += "file://2.22/0001-Backport-Fix-missing-exception-checks-and-handling-i.patch"
SRC_URI += "file://2.22/0001-Fix-offset-inconsistency-logs.patch"
SRC_URI += "file://2.22/0003-Backport-DeferGC-calls.patch"
SRC_URI += "file://2.22/0003-Add-register-r30-s8-to-Conservative-roots.patch"
SRC_URI += "file://2.22/0286-Implement-jump-step-positions.patch"
SRC_URI += "file://2.22.2/0288-Temporary-disable-cookies-in-gst-context.patch"

SRC_URI += "file://2.22.2/0289-GST-return-cached-position-when-sink-returns-invalid.patch"
#SRC_URI += "file://2.22.3/0290-Revert-Add-support-for-RTCPeerConnection.onicecandid.patch"
SRC_URI += "file://2.22.2/0290-Tweaking-libsoup-config-in-webkitwebsrc.patch"
SRC_URI += "file://2.22.2/0292-Increase-MSE-appsrc-buffering-min-percent.patch"
SRC_URI += "file://2.22.2/0293-Enable-wait-video-on-aml-audio-sink.patch"
SRC_URI += "file://2.22.3/0291-increase-micro-version-version-to-match-recipe.patch"
SRC_URI += "file://2.22.3/0292-fixing-rwi-ui-issues.patch"

SRC_URI += "file://2.22/0001-Rework-decryptor-attachment.patch"
SRC_URI += "file://2.22/0002-Use-isolatedCopy-for-strings-that-can-be-used-in-different-threads.patch"
SRC_URI += "file://2.22/0003-Fix-use-after-free-in-CoordinatedGraphicsLayer-destruction.patch"
SRC_URI += "file://2.22.3/0295-enable-inspector-server-based-on-remote-inspector-co.patch"
SRC_URI += "file://2.22.3/0296-Release-resources-in-suspended-state.patch"
SRC_URI += "file://2.22.3/0297-Fix-loss-of-ReadyForMoreSamples-notificat.patch"
SRC_URI += "file://2.22.3/0298-disable-xrun-timer-amlhalasink.patch"
SRC_URI += "file://2.22.3/0297-remove-the-existing-buffers-while-doing-rewind.patch"
SRC_URI += "file://2.22.3/0298-Let-eviction-algorith-to-cleanup-decoding.patch"

# device specific configs
PACKAGECONFIG[westeros] = "-DUSE_WPEWEBKIT_BACKEND_WESTEROS=ON -DUSE_WPEWEBKIT_PLATFORM_WESTEROS=ON -DUSE_KEY_INPUT_HANDLING_LINUX_INPUT=OFF -DUSE_GSTREAMER_HOLEPUNCH=ON -DUSE_EXTERNAL_HOLEPUNCH=ON -DUSE_WESTEROS_SINK=ON,,westeros"
PACKAGECONFIG[encryptedmedia] = "-DENABLE_ENCRYPTED_MEDIA=ON,-DENABLE_ENCRYPTED_MEDIA=OFF,"

PACKAGECONFIG[mathml] = "-DENABLE_MATHML=ON,-DENABLE_MATHML=OFF,"
PACKAGECONFIG[touchevents] = "-DENABLE_TOUCH_EVENTS=ON,-DENABLE_TOUCH_EVENTS=OFF,"
PACKAGECONFIG[meterelement] = "-DENABLE_METER_ELEMENT=ON,-DENABLE_METER_ELEMENT=OFF,"
PACKAGECONFIG[svgfonts] = "-DENABLE_SVG_FONTS=ON,-DENABLE_SVG_FONTS=OFF,"
PACKAGECONFIG[remoteinspector] = "-DENABLE_REMOTE_INSPECTOR=ON,-DENABLE_REMOTE_INSPECTOR=OFF,"
PACKAGECONFIG[vp9] = ",,,gstreamer1.0-plugins-good-matroska"
PACKAGECONFIG[vp9_hdr] = "-DENABLE_VP9_HDR=ON,-DENABLE_VP9_HDR=OFF,,gstreamer1.0-plugins-good-matroska"
PACKAGECONFIG[encryptedlocalstorage] = "-DENABLE_SQLITE_ENCRYPTION_EXTENSION=ON,-DENABLE_SQLITE_ENCRYPTION_EXTENSION=OFF,sqlite3see"
PACKAGECONFIG[shared_jsc] = "-DENABLE_SHARED_JSC=ON,,"
PACKAGECONFIG[gstreamergl] = "-DUSE_GSTREAMER_GL=ON,-DUSE_GSTREAMER_GL=OFF,"
PACKAGECONFIG[mediastream] = "-DENABLE_MEDIA_STREAM=ON -DENABLE_WEB_RTC=ON,-DENABLE_MEDIA_STREAM=OFF -DENABLE_WEB_RTC=OFF,libevent,libevent"
PACKAGECONFIG[webrtcusebuiltinopus] = "-DENABLE_WEBRTC_USE_BUILTIN_OPUS=ON,-DENABLE_WEBRTC_USE_BUILTIN_OPUS=OFF,libopus"
PACKAGECONFIG[asan] = "-DENABLE_ADDRESS_SANITIZER=ON,-DENABLE_ADDRESS_SANITIZER=OFF,gcc-sanitizers"
PACKAGECONFIG[hevc] = "-DENABLE_HEVC=ON,-DENABLE_HEVC=OFF,,"
PACKAGECONFIG[dolbyvision] = "-DENABLE_DV=ON,-DENABLE_DV=OFF,,"

PACKAGECONFIG_append = " intl"
PACKAGECONFIG_append = " remoteinspector"
#PACKAGECONFIG_append = " gamepad"

LEAD_SONAME = "libWPEWebKit-0.1.so"
FILES_${PN}-web-inspector-plugin += " ${libdir}/wpe-webkit-*/libWPEWebInspectorResources.so"

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
    export CMAKE_BUILD_PARALLEL_LEVEL=1
    ${STAGING_BINDIR_NATIVE}/ninja ${PARALLEL_MAKE}
}

do_install() {
    export CMAKE_BUILD_PARALLEL_LEVEL=1
    bbnote DESTDIR='${D}' cmake --build '${B}' --target install -- ${EXTRA_OECMAKE_BUILD}
    DESTDIR='${D}' cmake --build '${B}' --target install -- ${EXTRA_OECMAKE_BUILD}

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
    cp -ar ${S}/Source/WebKit/Shared/API/c/* ${D}${prefix}/include/WebKit/
    cp -ar ${S}/Source/WebKit/UIProcess/API/C/* ${D}${prefix}/include/WebKit/
    cp -ar ${S}/Source/WebKit/UIProcess/API/cpp/* ${D}${prefix}/include/WebKit/
    cp -ar ${S}/Source/WebKit/UIProcess/API/C/soup/* ${D}${prefix}/include/WebKit/

    # install pkg-config files
    install -d ${D}${libdir}/pkgconfig/
    cp -av --no-preserve=ownership ${B}/wpe-webkit-0.1.pc ${D}${libdir}/pkgconfig/

    if [ -e ${B}/lib/libWPEJavaScriptCore.so ]; then
        cp -av --no-preserve=ownership ${B}/lib/libWPEJavaScriptCore.so* ${D}${libdir}/
        chrpath --delete ${D}${libdir}/libWPEJavaScriptCore.so
        cp -av --no-preserve=ownership ${B}/Source/JavaScriptCore/wpe-javascriptcore.pc ${D}${libdir}/pkgconfig/
    fi
}
