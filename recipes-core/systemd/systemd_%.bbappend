FILESEXTRAPATHS_prepend := "${THISDIR}/files:${THISDIR}/backports:"

PACKAGECONFIG_remove = "vconsole ldconfig"
PACKAGECONFIG_remove_dunfell = "networkd"
PACKAGECONFIG_remove_dunfell = "resolved"
PACKAGECONFIG_remove_dunfell = "nss-resolve"

PACKAGECONFIG_remove_libc-uclibc = "sysusers machined"

EXTRA_OECONF += "--disable-ldconfig"
EXTRA_OECONF_append_libc-uclibc = " --disable-sysusers --disable-machined "

CFLAGS_append_arm = " -fno-lto"

SRC_URI += " \
	    file://50-coredump.conf \
	    file://50-panic.conf \
	    file://50-netfilter.conf \
           file://50-portreserv.conf \
           file://traffic-filter.conf \
           "
SRC_URI_append = " \
            file://usb-mount@.service \
            file://usb-mount.sh \
            file://99-usb-mount.rules \
           "
BACKPORTS ?= " "

RRECOMMENDS_${PN} += " \
        util-linux-swaponoff util-linux-losetup \
        util-linux-libmount util-linux-umount \
"

PACKAGES += "${PN}-usb-support"

FILES_${PN}-usb-support = " \
        /usb \
        /usb0 \
        /usb1 \
        ${systemd_unitdir}/system/usb-mount@.service \
        ${sbindir}/usb-mount.sh \
        ${sysconfdir}/udev/rules.d/99-usb-mount.rules \
        ${rootlibexecdir}/udev/rules.d/99-usb-mount.rules \
       "

FILES_${PN}_append_dunfell = " ${datadir}/bash-completion"
FILES_${PN}_append_dunfell = " ${sbindir}/usb-mount.sh"

do_install_append() {
	install -d ${D}${sysconfdir}/sysctl.d
	install -d ${D}${localstatedir}/lib/systemd/coredump
	install -m 644 ${WORKDIR}/50-coredump.conf ${D}${sysconfdir}/sysctl.d
	install -m 644 ${WORKDIR}/50-panic.conf ${D}${sysconfdir}/sysctl.d
	install -m 644 ${WORKDIR}/50-netfilter.conf ${D}${sysconfdir}/sysctl.d
	install -m 644 ${WORKDIR}/traffic-filter.conf ${D}${sysconfdir}/sysctl.d
        mkdir -pv ${D}/usb
        mkdir -pv ${D}/usb0
        mkdir -pv ${D}/usb1
        install -D -m 0644 ${S}/../usb-mount@.service ${D}${systemd_unitdir}/system/usb-mount@.service
        install -D -m 0755 ${S}/../usb-mount.sh ${D}${sbindir}/usb-mount.sh
        install -D -m 0644 ${S}/../99-usb-mount.rules ${D}${sysconfdir}/udev/rules.d/99-usb-mount.rules
        install -D -m 0644 ${S}/../99-usb-mount.rules ${D}${rootlibexecdir}/udev/rules.d/99-usb-mount.rules
        ln -s /dev/null ${D}${sysconfdir}/udev/rules.d/80-net-setup-link.rules

        sed -i -e 's/^#DumpCore=.*$/DumpCore=yes/g' ${D}${sysconfdir}/systemd/system.conf
        sed -i -e 's/^#DumpCore=.*$/LogColor=no/g' ${D}${sysconfdir}/systemd/system.conf
        sed -i -e 's/^#DefaultLimitCORE=.*$/DefaultLimitCORE=infinity/g' ${D}${sysconfdir}/systemd/system.conf

        sed -i -e 's/^#DumpCore=.*$/LogColor=no/g' ${D}${sysconfdir}/systemd/user.conf
        sed -i -e 's/^#DefaultLimitCORE=.*$/DefaultLimitCORE=infinity/g' ${D}${sysconfdir}/systemd/user.conf
        #Journal conf overide
        sed -i -e 's/.*ForwardToSyslog=.*/ForwardToSyslog=yes/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*Storage=.*/Storage=volatile/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*SystemMaxUse=.*/SystemMaxUse=16M/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*RuntimeMaxUse=.*/RuntimeMaxUse=16M/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*RuntimeMaxFileSize=.*/RuntimeMaxFileSize=4M/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*SystemMaxFileSize=.*/SystemMaxFileSize=4M/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*RateLimitInterval=.*/RateLimitInterval=0/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*RateLimitIntervalSec=.*/RateLimitIntervalSec=0/g' ${D}${sysconfdir}/systemd/journald.conf
        sed -i -e 's/.*RateLimitBurst=.*/RateLimitBurst=0/g' ${D}${sysconfdir}/systemd/journald.conf

        sed -i -e 's/10d/-/g' ${D}${exec_prefix}/lib/tmpfiles.d/tmp.conf
        sed -i -e 's/30d/-/g' ${D}${exec_prefix}/lib/tmpfiles.d/tmp.conf
        rm -rf ${D}${bindir}/busctl
        rm -rf ${D}${datadir}/bash-completion/completions/busctl
        rm -rf ${D}${libdir}/libnss_mymachines.so.2
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-bus-proxyd
if ${@bb.utils.contains('EXTRA_OECONF', '--enable-hwselftest', 'false', 'true', d)}; then
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-socket-proxyd
fi
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-ac-power
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-fsck
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-sleep
        rm -rf ${D}${rootlibexecdir}/systemd/system/systemd-fsck*.service
	rm -rf ${D}${rootlibexecdir}/systemd/systemd-reply-password
	rm -rf ${D}${rootlibexecdir}/systemd/systemd-activate
	sed -i -e 's/systemd-fsck-root.service//g' ${D}${systemd_unitdir}/system/systemd-remount-fs.service
if ! ${@bb.utils.contains('PACKAGECONFIG', 'resolved', 'true', 'false', d)}; then
        sed -i -e '/^L! \/etc\/resolv\.conf*/d' ${D}${exec_prefix}/lib/tmpfiles.d/etc.conf
fi
}

do_install_append_client() {
        install -d ${D}/media/apps
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-binfmt
        rm -rf ${D}${rootlibexecdir}/systemd/system/systemd-binfmt.service
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-update-done
        rm -rf ${D}${rootlibexecdir}/systemd/system/systemd-update-done.service
        rm -rf ${D}${rootlibexecdir}/systemd/system/sysinit.target.wants/systemd-update-done.service
        sed -i -e 's/systemd-update-done.service//g' ${D}${systemd_unitdir}/system/systemd-journal-catalog-update.service
        sed -i -e 's/systemd-update-done.service//g' ${D}${systemd_unitdir}/system/systemd-sysusers.service
}

do_install_append_hybrid() {
        install -d ${D}/media/apps
        install -m 644 ${WORKDIR}/50-portreserv.conf ${D}${sysconfdir}/sysctl.d
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-binfmt
        rm -rf ${D}${rootlibexecdir}/systemd/system/systemd-binfmt.service
        rm -rf ${D}${rootlibexecdir}/systemd/systemd-update-done
        rm -rf ${D}${rootlibexecdir}/systemd/system/systemd-update-done.service
        rm -rf ${D}${rootlibexecdir}/systemd/system/sysinit.target.wants/systemd-update-done.service
        sed -i -e 's/systemd-update-done.service//g' ${D}${systemd_unitdir}/system/systemd-journal-catalog-update.service
        sed -i -e 's/systemd-update-done.service//g' ${D}${systemd_unitdir}/system/systemd-sysusers.service
}

SYSTEMD_SERVICE_systemd-binfmt_remove = " systemd-binfmt.service"
SYSTEMD_SERVICE_${PN} += "usb-mount@.service"
SYSTEMD_SERVICE_${PN}_remove_broadband += "usb-mount@.service"
FILES_${PN} += "${sysconfdir}/sysctl.d/50-coredump.conf \
                ${sysconfdir}/sysctl.d/50-panic.conf \
               "

FILES_${PN} += "${sysconfdir}/sysctl.d/50-netfilter.conf \
               "
FILES_${PN}_remove = "${bindir}/busctl ${datadir}/bash-completion/completions/busctl ${libdir}/libnss_mymachines.so.2 ${rootlibexecdir}/systemd/systemd-bus-proxyd ${rootlibexecdir}/systemd/systemd-socket-proxyd ${rootlibexecdir}/systemd/systemd-ac-power ${rootlibexecdir}/systemd/systemd-fsck ${rootlibexecdir}/systemd/systemd-sleep ${rootlibexecdir}/systemd/system/systemd-fsck*.service ${rootlibexecdir}/systemd/systemd-reply-password ${rootlibexecdir}/systemd/systemd-activate"

FILES_${PN}_append_client = " /media/apps"
FILES_${PN}_append_hybrid = " /media/apps"
FILES_${PN}_append_hybrid += "${sysconfdir}/sysctl.d/50-portreserv.conf"

SYSTEMD_SERVICE_systemd-binfmt_remove_hybrid = " systemd-binfmt.service"
SYSTEMD_SERVICE_systemd-binfmt_remove_client = " systemd-binfmt.service"
