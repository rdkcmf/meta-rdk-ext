FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append  = " file://dropbear_2017-ssh_log.patch"
SRC_URI_append  = " file://ssh_telemetry_2017.patch"

CFLAGS_append_broadband = " -DRDK_BROADBAND"
