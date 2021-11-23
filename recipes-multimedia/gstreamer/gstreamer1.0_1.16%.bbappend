FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'enable_firebolt_compliance_tdk', 'file://0039-Add-Log-Level-Configuration-Support-TDK-gstcheck.patch', '', d)} \
"
