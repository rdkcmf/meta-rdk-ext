FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-DELIA-26739-high-memory-usage-for-dunfell.patch \
            file://0003-DELIA-42416-CallStranger-fix-for-dunfell.patch \
            file://0001-RDK-24171-libgupnp-devprotection.patch \
            "
SRC_URI_append_hybrid = " file://0001-RDK-17191-XUPNP-Xi-IP-Fetch-for-Dunfell.patch"
