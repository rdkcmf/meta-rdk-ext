
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
            file://0001-libexecdir-location.patch \
           "
SRC_URI_append_hybrid += "file://0002-bluetooth_autoenable_policy_main_conf.patch"
SRC_URI_append_client += "file://0002-bluetooth_autoenable_policy_main_conf.patch"
