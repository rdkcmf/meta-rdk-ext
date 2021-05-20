FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
	   file://0001-Add-initial-support-for-the-ambient-set.patch \
	   file://0002-Clean-up-ambient-support.patch \
	   file://0003-Update-uapi-headers.patch \
           "
