DEPENDS +="linux-libc-headers"
do_install_append(){
    if [ "${COMCAST_GPERFTOOLS_HEAPCHECK_WP_DISTRO_ENABLED}" != "1" ]; then
        rm -rf ${D}${bindir}/perl*
    fi
    rm  ${D}${libdir}/perl5
}

FILES_${PN}_remove = "${@bb.utils.contains('DISTRO_FEATURES_RDK', 'comcast-gperftools-heapcheck-wp', '', '${bindir}/perl*', d)}"
FILES_${PN}-lib_remove = "${libdir}/perl5"
