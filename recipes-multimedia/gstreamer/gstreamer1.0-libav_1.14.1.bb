require gstreamer1.0-libav.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                    file://ext/libav/gstav.h;beginline=1;endline=18;md5=a752c35267d8276fd9ca3db6994fca9c \
                    file://gst-libs/ext/libav/COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://gst-libs/ext/libav/COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://gst-libs/ext/libav/COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://gst-libs/ext/libav/COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "http://gstreamer.freedesktop.org/src/gst-libav/gst-libav-${PV}.tar.xz \
           file://0001-Disable-yasm-for-libav-when-disable-yasm.patch \
           file://workaround-to-build-gst-libav-for-i586-with-gcc.patch \
           file://mips64_cpu_detection.patch \
           file://0001-configure-check-for-armv7ve-variant.patch \
           file://0002-gst-libs-Remove-library-path-switch-from-dependency_.patch \
           "
SRC_URI[md5sum] = "d57b0052f6eccb8e6762def05995133c"
SRC_URI[sha256sum] = "eff80a02d2f2fb9f34b67e9a26e9954d3218c7aa18e863f2a47805fa7066029d"

LIBAV_EXTRA_CONFIGURE_COMMON_ARG = "--target-os=linux \
  --cc='${CC}' --as='${CC}' --ld='${CC}' --nm='${NM}' --ar='${AR}' \
  --ranlib='${RANLIB}' \
  ${GSTREAMER_1_0_DEBUG} \
  --cross-prefix='${HOST_PREFIX}'"

PACKAGECONFIG_append = "yasm"

PACKAGECONFIG[gpl] = "--enable-gpl,--disable-gpl,"
PACKAGECONFIG[yasm] = "--enable-yasm,--disable-yasm,yasm-native"
PACKAGECONFIG[valgrind] = "--enable-valgrind,--disable-valgrind,valgrind"

PACKAGECONFIG_remove_linux-gnux32 = "yasm"
LIBAV_EXTRA_CONFIGURE_COMMON_ARG_append_linux-gnux32 = " --disable-asm"

# http://errors.yoctoproject.org/Errors/Details/20493/
ARM_INSTRUCTION_SET_armv4 = "arm"
ARM_INSTRUCTION_SET_armv5 = "arm"

# ffmpeg/libav disables PIC on some platforms (e.g. x86-32)
INSANE_SKIP_${PN} = "textrel"


S = "${WORKDIR}/gst-libav-${PV}"
