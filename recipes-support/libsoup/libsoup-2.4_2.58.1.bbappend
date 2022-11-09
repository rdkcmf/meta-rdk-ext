FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://soup_cookie_jar_limit.patch \
            file://interleave_addresses.patch \
            file://0001-tld-parser-to-use-python3.patch \
            file://libsoup-devprotection.patch \
            file://failed_tls_print_ip_address.patch \
            file://error_on_incomplete_chunked_encoding.patch \
            file://0001-RDKB-39013-Continuous-flooding-of-libsoup-tls-error-.patch \
            "

