SUMMARY = "Plymouth is a project from Fedora providing a flicker-free graphical boot process."

DESCRIPTION = "Plymouth is an application that runs very early in the boot process (even before the root filesystem is mounted!) that provides a graphical boot animation while the boot process happens in the background."
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/Plymouth"
SECTION = "base"

LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libpng cairo libdrm dbus"

SRC_URI = "http://www.freedesktop.org/software/plymouth/releases/${BPN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "38f5e613e5ab17806b950cee2d0d0d4e"
SRC_URI[sha256sum] = "1bada4e1d3a31a5a99adc2db83f5452d9818839cda51a6e430f044f6281d759b"

EXTRA_OECONF += " --enable-shared --disable-static --enable-libkms \
                  --disable-gtk --disable-pango \
                  --enable-systemd-integration --with-system-root-install "

EXTRA_OECONF_append_x86 = " --enable-drm --enable-libdrm_intel --enable-libdrm_radeon"
EXTRA_OECONF_append_x86-64 = " --enable-drm --enable-libdrm_intel --enable-libdrm_radeon"

FILES_${PN} += "${systemd_unitdir}/system/*"
FILES_${PN}-dbg += "${libdir}/plymouth/renderers/.debug"

inherit autotools pkgconfig systemd

SYSTEMD_SERVICE_${PN} = "plymouth-start.service"

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 644 ${B}/systemd-units/*.service ${D}${systemd_unitdir}/system
	install -m 644 ${B}/systemd-units/systemd-ask-password-plymouth.path ${D}${systemd_unitdir}/system
	# Remove /var/run from package as plymouth will populate it on startup
	rm -fr "${D}${localstatedir}/run"

}
