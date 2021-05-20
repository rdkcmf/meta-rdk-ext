FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
            file://0002-libexecdir-location.patch \
            file://0001-testtools-fix-SIOCGSTAMP-undeclared-error.patch \
            file://0003-bluez5_DisableSecondaryServices.patch \
           "
SRC_URI_append_hybrid += "file://bluetooth_autoenable_policy_main_conf.patch"
SRC_URI_append_client += "file://bluetooth_autoenable_policy_main_conf.patch"
