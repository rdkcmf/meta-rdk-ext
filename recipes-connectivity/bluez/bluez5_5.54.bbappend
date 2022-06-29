
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
            file://0001-libexecdir-location.patch \
            file://CVE-2020-27153.patch \
            file://0004-bluetooth_avdtp_a2dp_abort.patch \
           "
SRC_URI_append_hybrid += "file://0002-bluetooth_autoenable_policy_main_conf.patch"
SRC_URI_append_client += "file://0002-bluetooth_autoenable_policy_main_conf.patch"
