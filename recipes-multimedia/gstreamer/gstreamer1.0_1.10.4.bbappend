FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0001-protection-added-function-to-filter-system-ids.patch \
                   file://0004-protection-Add-a-new-definition-for-unspecified-syst.patch \
                   file://0005-protection-Fix-the-string-to-define-unspecified-syst.patch \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'enable_firebolt_compliance_tdk', 'file://0039-Add-Log-Level-Configuration-Support-TDK-1.10gstcheck.patch', '', d)} \
                   file://0001-calculating-the-bytes-for-seeked-position.patch \
"

