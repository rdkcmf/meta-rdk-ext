FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append  = " file://dropbear_2017-ssh_log.patch"