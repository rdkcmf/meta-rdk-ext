include ${@bb.utils.contains('DISTRO_FEATURES', 'systemd-profile', 'profiles/rdk-${BPN}-profile-${PV}.inc', 'profiles/generic-profile.inc', d)}

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PACKAGECONFIG_remove_dunfell = " pam idn quotacheck randomseed logind  hostnamed timedated localed resolve coredump tpm"

SRC_URI += "file://0001-DELIA-44564-fix-journalctl-244-log-format.patch"
