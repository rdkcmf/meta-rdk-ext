FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://CVE-2017-18269.patch \
	    file://CVE-2018-11237.patch \
	    file://CVE-2019-9169.patch \
"

SRC_URI_append_broadband = " file://ignore-truncated-dns-response.patch"
