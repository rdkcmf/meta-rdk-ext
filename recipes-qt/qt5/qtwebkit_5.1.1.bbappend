inherit gettext
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# if DLNA is disabled, we use plain qtwebkit, with no RMF depends, and we disable DLNA in the patches
# if playersinkbin (generic mediaplayersink) is disabled, we depend on SoC mediaplayersink
DLNA_DEPENDS = "rmfgeneric"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'rdk-dlna', '${DLNA_DEPENDS}', '', d)}"

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('DISTRO_FEATURES', 'rdk-dlna', 'DEFINES+=ENABLE_DLNA_VIDEO_PLAYER', '', d)}"

PACKAGECONFIG = "${@bb.utils.contains('DISTRO_FEATURES', 'gstreamer1', 'gstreamer', 'gstreamer010', d)}"

ERROR_QA_remove_morty = "pkgconfig"

ERROR_QA_remove_krogoth = "pkgconfig"

DEPENDS_remove = "qtdeclarative"
