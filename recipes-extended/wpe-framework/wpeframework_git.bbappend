# Added breakpad-wrapper as depends for wpeframework
DEPENDS_append = "${@bb.utils.contains('DISTRO_FEATURES', 'logbacktrace', 'breakpad-wrapper', '', d)}"
