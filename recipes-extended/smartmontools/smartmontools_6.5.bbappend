FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://smartmontools-6.5_print_optimize.patch "

do_install_append() {
       rm -rf ${D}${datadir}/smartmontools
}

FILES_${PN}_remove +="${datadir}/smartmontools"
