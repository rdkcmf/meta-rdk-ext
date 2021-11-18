FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_remove_dunfell = " file://verbose.patch \
                           file://revsshipv6.patch \
                           file://0001-Fixed-Race-Conditions-Observed-when-using-port-forwa.patch \
"

SRC_URI_append_dunfell = " file://dropbear_2019-verbose.patch \
                           file://dropbear_2019-revsshipv6.patch \
                           file://dropbear_2019-Fixed-Race-Conditions-Observed-when-using-port-forwa.patch \
                           file://dropbear_2019-CVE-2020-36254.patch \
"

do_install_append() {
        if [ "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}" = "systemd" ]; then
                sed -i -- '/EnvironmentFile=.*/a BindToDevice=${ESTB_INTERFACE}' ${D}${systemd_unitdir}/system/dropbear@.service
                sed -i -- '/EnvironmentFile=.*/a EnvironmentFile=/etc/device.properties' ${D}${systemd_unitdir}/system/dropbear@.service
        fi
}
