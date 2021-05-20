FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI_append = "\
                  file://0001-ipv4-try-to-cache-dst_entries-which-would-cause-a-re.patch \
                 "
