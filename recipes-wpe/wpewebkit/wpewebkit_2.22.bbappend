FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
# patches for rdkbrowser2 support
RDKBROWSER2_PATCHES = " \
    file://0030-Adding-an-API-to-configure-MediaCacheDirectory-in-WK_0.4.patch \
    file://0142-Add-API-to-explicitly-request-memory-release.patch \
    file://0168-comcast-Add-API-to-set-IngoreResize-in-WPEView.patch \
    file://0064-wpe-accessibility_0.4.3.patch \
    file://0201-fixed-build-error-on-JSC.patch \
"
SRC_URI += "${@bb.utils.contains('DISTRO_FEATURES', 'rdkbrowser2', '${RDKBROWSER2_PATCHES}', '', d)}"

# atk dependency is for wpe accessibility patch
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'rdkbrowser2', 'atk', '', d)}"

##### headers exposed to compile rdkbrowser2
DEPENDS += "rsync-native"

do_install_append_dunfell () {
    #install header files
    mkdir -p ${D}${includedir}/WebKit
    cp -ar ${B}/DerivedSources/ForwardingHeaders/WebKit ${D}${prefix}/include
    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${D}${includedir}/wpe-webkit-deprecated-0.1/WPE/WebKit/ ${D}${includedir}/WebKit
    install -m 0644 ${S}/Source/WebKit/UIProcess/API/cpp/*.h ${D}${includedir}/WebKit
    install -m 0644 ${S}/Source/WebKit/UIProcess/API/C/*.h ${D}${includedir}/WebKit

   mkdir -p ${D}${includedir}/WebKit/Shared/API/c/
    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/WebKit/Shared/API/c/ ${D}${includedir}/WebKit/Shared/API/c/

   mkdir -p ${D}${includedir}/WebKit/UIProcess/API/C/
    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
     ${S}/Source/WebKit/UIProcess/API/C/ ${D}${includedir}/WebKit/UIProcess/API/C/

    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/WTF/wtf ${D}${includedir}/

    mkdir -p ${D}${includedir}/JavaScriptCore/
    rsync -rv  --include '*/' --include '*.h' --exclude '*' \
          ${S}/Source/JavaScriptCore/API/ ${D}${includedir}/JavaScriptCore/
}
