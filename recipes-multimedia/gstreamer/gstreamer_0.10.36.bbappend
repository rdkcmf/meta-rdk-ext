# To not violate GPL license, parse is disabled, it means that gst launch will not work.
#EXTRA_OECONF += "--disable-parse"


# EXTRA_OECONF = "--disable-docs-build --disable-dependency-tracking --with-check=no --disable-examples --disable-tests --disable-valgrind ${GSTREAMER_DEBUG}"
# 
# option_configure_args := --disable-loadsave --disable-tests --disable-examples --disable-failing-tests \
# --disable-net --disable-docbook --disable-gtk-doc \
# --enable-nls --enable-largefile --enable-registry
# ifeq ($(GST_DONT_BUILD_GPL_V3),YES)
# option_configure_args += --disable-parse
# endif

EXTRA_OECONF += "--enable-check"

do_install_append() {
	install -d ${D}${includedir}/gstreamer-0.10/gst/check
	install -m 0644 ${S}/libs/gst/check/*.h ${D}${includedir}/gstreamer-0.10/gst/check
	install -m 0644 ${S}/libs/gst/check/libcheck/check.h ${D}${includedir}/gstreamer-0.10/gst/check/internal-check.h
}
