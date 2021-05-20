do_install_append () {
	if [ "${USE_LDCONFIG}" != "1" ]; then
		rm -f ${D}${base_sbindir}/ldconfig
	fi
	#RDK-20101: Extra files for Eglibc in XG devices
        rm -rf ${D}${base_libdir}/libanl*
        rm -rf ${D}${base_libdir}/libBrokenLocale*
}
