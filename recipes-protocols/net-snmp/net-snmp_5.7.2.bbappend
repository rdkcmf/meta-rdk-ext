
FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"
SRC_URI += "file://rdk_enhancement.patch \
            file://Handle-duplicates-in-a-binary_array-container.patch \
            file://net-snmp-agentx-crash.patch \
            file://netsnmp-mibindex-lookup-crash.patch \
"
SRC_URI_append_broadband = " \
            file://rdkb_snmp.patch \
"
