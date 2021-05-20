FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://verbose.patch"
SRC_URI_append = " file://revsshipv6.patch"
SRC_URI_append = " file://0001-Fixed-Race-Conditions-Observed-when-using-port-forwa.patch"

do_install_append() {
    	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
		sed -i -- '/EnvironmentFile=.*/a BindToDevice=${ESTB_INTERFACE}' ${D}${systemd_unitdir}/system/dropbear@.service
		sed -i -- '/EnvironmentFile=.*/a EnvironmentFile=/etc/device.properties' ${D}${systemd_unitdir}/system/dropbear@.service
	fi
}
