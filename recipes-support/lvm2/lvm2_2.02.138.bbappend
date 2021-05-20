EXTRA_OECONF_append = " --enable-applib=no --enable-cmdlib=no --enable-dmeventd=no --enable-lvmetad=no "

SYSTEMD_SERVICE_${PN}_remove = "lvm2-monitor.service dm-event.socket dm-event.service lvm2-lvmetad.socket \
                         lvm2-pvscan@.service blk-availability.service"

do_install_append(){
        rm -rf ${D}${base_libdir}/udev/rules.d/10-dm.rules
}
