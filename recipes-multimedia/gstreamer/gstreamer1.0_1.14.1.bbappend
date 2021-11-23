FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0004-protection-Add-a-new-definition-for-unspecified-syst.patch \
                   file://0005-protection-Fix-the-string-to-define-unspecified-syst.patch \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'enable_firebolt_compliance_tdk', 'file://0039-Add-Log-Level-Configuration-Support-TDK-gstcheck.patch', '', d)} \
"

