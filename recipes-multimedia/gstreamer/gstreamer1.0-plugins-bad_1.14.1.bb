require gstreamer1.0-plugins-bad.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 "

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-bad/gst-plugins-bad-${PV}.tar.xz \
    file://configure-allow-to-disable-libssh2.patch \
    file://fix-maybe-uninitialized-warnings-when-compiling-with-Os.patch \
    file://avoid-including-sys-poll.h-directly.patch \
    file://ensure-valid-sentinels-for-gst_structure_get-etc.patch \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://0001-Prepend-PKG_CONFIG_SYSROOT_DIR-to-pkg-config-output.patch \
"
SRC_URI[md5sum] = "c2f963501fc6afc084c418894425dcfd"
SRC_URI[sha256sum] = "2a77c6908032aafdf2cd2e5823fec948f16a25c2d1497a953828d762dc20d61a"

PACKAGECONFIG_append = " sndfile ttml uvch264 webp"

PACKAGECONFIG[sndfile]         = "--enable-sndfile,--disable-sndfile,libsndfile1"
PACKAGECONFIG[srtp]            = "--enable-srtp,--disable-srtp,libsrtp"
PACKAGECONFIG[ttml]            = "--enable-ttml,--disable-ttml,libxml2 pango cairo"
PACKAGECONFIG[uvch264]         = "--enable-uvch264,--disable-uvch264,libusb1 libgudev"
PACKAGECONFIG[voaacenc]        = "--enable-voaacenc,--disable-voaacenc,vo-aacenc"
PACKAGECONFIG[voamrwbenc]      = "--enable-voamrwbenc,--disable-voamrwbenc,vo-amrwbenc"
PACKAGECONFIG[vulkan]          = "--enable-vulkan,--disable-vulkan,vulkan"
PACKAGECONFIG[webp]            = "--enable-webp,--disable-webp,libwebp"
PACKAGECONFIG[webrtc]          = "--enable-webrtc,--disable-webrtc,libnice"
PACKAGECONFIG[webrtcdsp]       = "--enable-webrtcdsp,--disable-webrtcdsp,webrtc-audio-processing"


S = "${WORKDIR}/gst-plugins-bad-${PV}"

# There is no RECIPE_SYSROOT for Morty, introduced only in Pyro
# In case of Multilib, wayland-protocols pkgconfig is installed in ${STAGING_DIR}/${MACHINE}, but STAGING_DIR_TARGET
# points to ${STAGING_DIR}/${MLPREFIX}${MACHINE}
# So make the equivalent of BPN for STAGING_DIR_TARGET
BSTAGING_DIR_TARGET = "${STAGING_DIR}/${MACHINE}"
WAYLAND_PROTOCOL_SYSROOT_DIR_morty = "${BSTAGING_DIR_TARGET}"
WAYLAND_PROTOCOL_SYSROOT_DIR = "${RECIPE_SYSROOT}"
EXTRA_OECONF += " WAYLAND_PROTOCOLS_SYSROOT_DIR=${WAYLAND_PROTOCOL_SYSROOT_DIR}"

