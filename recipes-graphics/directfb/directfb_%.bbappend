DEPENDS += "linux-fusion"
RDEPENDS_${PN} += "kernel-module-fusion"
EXTRA_OECONF += "--enable-multi"
PACKAGECONFIG[drmkms] = "--enable-drmkms,--disable-drmkms,libdrm"
PACKAGECONFIG[tiff] = "--enable-tiff,--disable-tiff,tiff"

DEPENDS_remove_rpi = "linux-fusion"
RDEPENDS_${PN}_remove_rpi = "kernel-module-fusion"
EXTRA_OECONF_remove_rpi = "--enable-multi"
