FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_dunfell = " file://0001-fingerprint-dhcp-lease-file.patch \
            	   	   file://0002-lease-duplicate-hostnames.patch \
                           file://0003-client-notify-new.patch \
                           file://0001-XDNS-secondary-XDNS-feature-zombie-fix.patch \
                           file://0002-XDNS-log-protect-browsing-MultiProfile.patch \
                           "

