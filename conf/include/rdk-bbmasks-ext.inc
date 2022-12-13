# Use Bluez 5.48 for ControlMgr and BLE Voice support.
PREFERRED_VERSION_bluez5 = "${@bb.utils.contains('DISTRO_FEATURES', 'enable-rdkv-bt-voice', '5.48', '', d)}"
BBMASK .= "${@bb.utils.contains('DISTRO_FEATURES', 'enable-rdkv-bt-voice', '', '|meta-rdk-ext/recipes-connectivity/bluez/bluez5_5.48.bb', d)}"
