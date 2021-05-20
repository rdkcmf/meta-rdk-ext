FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"
# Add egl/gles to config
PACKAGECONFIG_append_class-target = " egl"
PACKAGECONFIG_append_class-target = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'glesv2', d)}"

SRC_URI += "file://0006-add-egl-device-create.patch"
SRC_URI += "file://0009-error-check-just-in-debug.patch"

SRC_URI += "file://0001-add-noaa-compositor-for-v1.16.patch"
