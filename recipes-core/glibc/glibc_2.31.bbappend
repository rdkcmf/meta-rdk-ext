FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_broadband = " file://0024-glibc-2.31-ignore-truncated-dns-response.patch"
SRC_URI_append = " file://0001-glibc-2.31-mips-clone-stack-alignment.patch"
