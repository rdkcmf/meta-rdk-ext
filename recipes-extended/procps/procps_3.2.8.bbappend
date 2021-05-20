FILESEXTRAPATHS_prepend:= "${THISDIR}/files:"

SRC_URI += "file://increase_status_buffer_to_1024_bytes.patch \
            file://print_blocked_dstate_stats.patch \
            file://libproc.pc \
            file://ignore_pkill_requests_with_empty_pattern.patch"

CFLAGS_append_mipsel = " -fno-fast-math"
CPPFLAGS_append_mipsel = " -fno-fast-math"

do_install_append() {
	install -d ${D}${includedir}/proc ${D}${includedir}/ps
	install -m 0755 ${S}/*.h ${D}${includedir}
	install -m 0755 ${S}/proc/*.h ${D}${includedir}/proc
	install -m 0755 ${S}/ps/*.h ${D}${includedir}/ps
        install -Dm644 ${S}/../libproc.pc ${D}${libdir}/pkgconfig/libproc.pc
}
