FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-undo-bad-eglibc-patch-for-__sin-and-__cos.patch \
            file://0001-Properly-fix-memory-leak-in-_nss_dns_gethostbyname4_.patch \
            file://Properly-fix-memory-leak_extra.patch \
            file://0003-BZ-16469-resolv-skip-leading-dot-in-domain-to-search.patch \
            file://0002-BZ-16469-don-t-drop-trailing-dot-in-res_nquerydomain.patch \
            file://0001-BZ-14498-fix-infinite-loop-in-nss_db_getservbyname.patch \
            file://0001-Do-not-fail-if-one-of-the-two-responses-to-AF_UNSPEC.patch \
            file://0001-CVE-2015-7547-getaddrinfo-stack-based-buffer-overflo.patch \
            file://buffer_overflow_extra.patch \
           "
