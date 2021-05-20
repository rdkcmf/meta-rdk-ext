do_install_append () {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		echo "export SYSTEMD_PAGER=/bin/cat" >> ${D}${sysconfdir}/profile
	fi
	if [ -e ${D}${sysconfdir}/fstab ]; then
		sed -i -e '/^\/dev\/root/ s/\([[:space:]]*[[:digit:]]\)\([[:space:]]*\)[[:digit:]]$/\1\20/' ${D}${sysconfdir}/fstab
	fi
}
