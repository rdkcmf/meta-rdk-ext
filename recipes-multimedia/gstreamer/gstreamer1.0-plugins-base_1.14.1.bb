require gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-base/gst-plugins-base-${PV}.tar.xz \
    file://get-caps-from-src-pad-when-query-caps.patch \
    file://0003-ssaparse-enhance-SSA-text-lines-parsing.patch \
    file://make-gio_unix_2_0-dependency-configurable.patch \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://0005-gstreamer-gl.pc.in-don-t-append-GL_CFLAGS-to-CFLAGS.patch \
    file://0006-glimagesink-Downrank-to-marginal.patch \
    file://0007-Add-lvchostif-to-link-when-using-lEGL-on-rpi.patch \
"
SRC_URI_append = "\
    file://0001-Makefile.am-don-t-hardcode-libtool-name-when-running.patch \
    file://0002-Makefile.am-prefix-calls-to-pkg-config-with-PKG_CONF.patch \
    file://0003-riff-add-missing-include-directories-when-calling-in.patch \
    file://0004-rtsp-drop-incorrect-reference-to-gstreamer-sdp-in-Ma.patch \
    file://gtk-doc-tweaks.patch \
    "

PACKAGECONFIG_append = "\
${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2 egl', '', d)}"

PACKAGECONFIG[egl]          = "--enable-egl,--disable-egl,virtual/egl"
PACKAGECONFIG[gio-unix-2.0] = "--enable-gio_unix_2_0,--disable-gio_unix_2_0,glib-2.0"
PACKAGECONFIG[gles2]        = "--enable-gles2,--disable-gles2,virtual/libgles2"
PACKAGECONFIG[opus]         = "--enable-opus,--disable-opus,libopus"

SRC_URI[md5sum] = "c42154ab6f85c59f0e449d8d7e290342"
SRC_URI[sha256sum] = "1026c7c3082d825d9b5d034c1a6dd8a4ebab60eb3738b0a0afde4ad2dc0b0db5"

DEPENDS += "iso-codes util-linux zlib libgudev libdrm"

S = "${WORKDIR}/gst-plugins-base-${PV}"

inherit gettext gtk-doc

PACKAGES_DYNAMIC =+ "^libgst.*"

do_configure[prefuncs] += " delete_pkg_m4_file"

delete_pkg_m4_file() {
        # This m4 file is out of date and is missing PKG_CONFIG_SYSROOT_PATH tweaks which we need for introspection
        rm "${S}/common/m4/pkg.m4" || true
        rm -f "${S}/common/m4/gtk-doc.m4"
}

