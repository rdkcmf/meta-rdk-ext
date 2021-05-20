FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://0002-math-link.patch"

#Fix - Cannot satisfy the following dependencies for yajl in daisy platforms
PACKAGE_BEFORE_PN = "${@bb.utils.contains('DISTRO_FEATURES','morty','${PN}-bin','',d)}"
