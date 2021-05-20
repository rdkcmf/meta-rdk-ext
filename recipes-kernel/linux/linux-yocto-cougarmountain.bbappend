FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "\
                  file://0001-add-support-for-http-host-headers-cookie-url-netfilt.patch \
                  file://http_netfilter.cfg \
                  file://nfsdisable.cfg \
                 "

