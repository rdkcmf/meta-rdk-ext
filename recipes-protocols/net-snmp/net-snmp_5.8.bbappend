FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"
CACHED_CONFIGUREVARS = "ac_cv_lib_crypto_EVP_md5=yes ac_cv_lib_crypto_AES_cfb128_encrypt=no"
CACHED_CONFIGUREVARS_append_dunfell = " ac_cv_file__etc_printcap=no"
RDEPENDS_${PN}_append_dunfell = " openssl"

SRC_URI += "file://rdk_enhancement.patch"

SRC_URI_append_dunfell = " file://systemd-support.patch \
                           file://sd-deamon_h.patch \
                           file://agent_registry.patch \
                         "

SRC_URI_append_broadband = " \
            file://rdkb_snmp.patch \
"

