FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	 file://soup_cookie_jar_limit_2_68.patch \
	 file://interleave_addresses.patch \
	 file://libsoup-devprotection_2_68.patch \
	 file://failed_tls_print_ip_address.patch \
	 file://error_on_incomplete_chunked_encoding.patch \
     file://0001-RDKB-44368-libsoup-2.68-Continuous-flooding-of-libso.patch \
	 file://DELIA-57838-cookie-jar-db-Fix-DB-access-BUSY-failure.patch \
"

# glib-openssl is needed for SSL, proxies, etc.
RRECOMMENDS_${PN} =+ "glib-openssl"
