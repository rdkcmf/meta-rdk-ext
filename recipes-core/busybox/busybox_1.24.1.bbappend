FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
 
SRC_URI_append = "\
		  file://busybox_tar_vulnerability_8411.patch \
 	          file://CVE-2017-16544.patch \
		  file://CVE-2018-1000517.patch \
"
