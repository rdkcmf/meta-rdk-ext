INHERIT_COMCAST_PACKAGE_DEPLOY := "${@bb.utils.contains('DISTRO_FEATURES', 'rdm benchmark_enable', 'comcast-package-deploy', '', d)}"
inherit ${INHERIT_COMCAST_PACKAGE_DEPLOY}
DOWNLOAD_APPS="${@bb.utils.contains('DISTRO_FEATURES','rdm benchmark_enable','${PN}-ptest',' ',d)}"
SKIP_MAIN_PKG="yes"
CUSTOM_PKG_EXTNS="ptest"
