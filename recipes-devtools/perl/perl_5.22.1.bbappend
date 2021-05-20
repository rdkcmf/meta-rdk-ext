PACKAGES =+ "${PN}-module-bin"

FILES_${PN}-module-bin += "${bindir}/perl* ${libdir}/perl5 ${libdir}/libperl.so*"
INSANE_SKIP_${PN}-module-bin = "dev-so"

FILES_${PN}_remove = "${bindir}/perl*"
FILES_${PN}-lib_remove = " \
	${libdir}/perl5 \
        ${libdir}/libperl.so* \
"
