SUMMARY = "GStreamer 1.0 multimedia framework"
DESCRIPTION = "GStreamer is a multimedia framework for encoding and decoding video and sound. \
It supports a wide range of formats including mp3, ogg, avi, mpeg and quicktime."
HOMEPAGE = "http://gstreamer.freedesktop.org/"
BUGTRACKER = "https://bugzilla.gnome.org/enter_bug.cgi?product=Gstreamer"
SECTION = "multimedia"
LICENSE = "LGPLv2+"
DEPENDS = "glib-2.0 glib-2.0-native libxml2 bison-native flex-native"

inherit autotools pkgconfig gettext gtk-doc

GSTREAMER_1_DEBUG ?= "--disable-debug"
EXTRA_OECONF = "--disable-docbook --disable-gtk-doc \
                --disable-dependency-tracking \
                --disable-examples --disable-tests \
                --disable-valgrind ${GSTREAMER_1_DEBUG} \
                --with-bash-completion-dir=no \
                --disable-introspection \
                "

RRECOMMENDS_${PN}_qemux86    += "kernel-module-snd-ens1370 kernel-module-snd-rawmidi"
RRECOMMENDS_${PN}_qemux86-64 += "kernel-module-snd-ens1370 kernel-module-snd-rawmidi"

CACHED_CONFIGUREVARS += "ac_cv_header_valgrind_valgrind_h=no"


delete_pkg_m4_file() {
        # This m4 file is out of date and is missing PKG_CONFIG_SYSROOT_PATH tweaks which we need for introspection
        rm "${S}/common/m4/pkg.m4" || true
        rm -f "${S}/common/m4/gtk-doc.m4"
}

# gstreamer is not using system-wide makefiles (which we patch in gtkdoc recipe,
# but its own custom ones, which we have to patch here
patch_gtk_doc_makefiles() {
        # Patch the gtk-doc makefiles so that the qemu wrapper is used to run transient binaries
        # instead of libtool wrapper or running them directly
        # Also substitute a bogus plugin scanner, as trying to run the real one is causing issues during build on x86_64.
        sed -i \
           -e "s|GTKDOC_RUN =.*|GTKDOC_RUN = \$(top_builddir)/gtkdoc-qemuwrapper|" \
           -e "s|\$(GTKDOC_EXTRA_ENVIRONMENT)|\$(GTKDOC_EXTRA_ENVIRONMENT) GST_PLUGIN_SCANNER_1_0=\$(top_builddir)/libs/gst/helpers/gst-plugin-scanner-dummy|" \
           ${S}/common/gtk-doc*mak
}

do_configure[prefuncs] += " delete_pkg_m4_file patch_gtk_doc_makefiles"

do_compile_prepend() {
        export GIR_EXTRA_LIBS_PATH="${B}/gst/.libs:${B}/libs/gst/base/.libs"
}

FILES_${PN} += " ${libdir}/gstreamer-1.0/*.so"
FILES_${PN}-dev += " ${libdir}/gstreamer-1.0/*.la ${libdir}/gstreamer-1.0/*.a"
FILES_${PN}-dbg += " ${libdir}/gstreamer-1.0/.debug/ ${libexecdir}/gstreamer-1.0/.debug/"

