FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "\
                  file://0001-add-support-for-http-host-headers-cookie-url-netfilt.patch \
                  file://http_netfilter.cfg \
                  file://0001-futexes-Increase-hash-table-size-for-better-performa.patch \
                  file://0001-futexes-Document-multiprocessor-ordering-guarantees.patch \
                  file://0001-futexes-Avoid-taking-the-hb-lock-if-there-s-nothing-.patch \
                  file://0001-futex-revert-back-to-the-explicit-waiter-counting-co.patch \
                  file://0001-futex-Ensure-get_futex_key_refs-always-implies-a-bar.patch \
                  file://mqueue.cfg \
                  file://nfsdisable.cfg \
                 "

SRC_URI_append_rdkzram = " \
                         file://zram.cfg \
                         "
