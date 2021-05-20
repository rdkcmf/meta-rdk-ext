inherit gettext
PACKAGECONFIG_GL = "gles2"
PACKAGECONFIG_FB = " "
PACKAGECONFIG_DISTRO = "icu examples sql-sqlite glib "

PACKAGECONFIG_DISTRO += " ${@bb.utils.contains('DISTRO_FEATURES', 'gstreamer1', 'gstreamer', 'gstreamer010', d)}"

PACKAGECONFIG_DISTRO += " pcre"


PACKAGECONFIG[printsupport] = ",-DQT_NO_PRINTER,,"

DEPENDS_append_dunfell = " dbus directfb libxkbcommon"
DEPENDS_append_dunfell_rpi = " libtirpc"
CXXFLAGS_append_dunfell_rpi = " -I${STAGING_INCDIR}/tirpc"

ERROR_QA_remove_morty = "pkgconfig"

ERROR_QA_remove_krogoth = "pkgconfig"

EXTRA_QMAKEVARS_PRE += "DEFINES+=QT_NO_PRINTER"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
	file://qt-5.1.1_qeglfs.patch \
	${@bb.utils.contains('PACKAGECONFIG_DISTRO', 'printsupport', '', 'file://disable-printsupport.patch', d)} \
	"


###Added install_append task to move fonts directory from libdir to datadir as per FHS compliance#######
do_configure_prepend () {

	# disable printsupport if we upgrade to 5.2+ then we can disable
	# it with configure
	# https://bugreports.qt.io/browse/QTBUG-33565
#	rm -rf ${S}/src/printsupport
#	rm -rf ${S}/plugins/printsupport
}
do_install_append() {
        install -d ${D}${datatdir}
        rm -rf ${D}${libdir}/fonts/*.pfb
        mv ${D}${libdir}/fonts ${D}${datadir}
	rm -rf ${D}${libdir}/cacert.pem
	rm -rf ${D}${libdir}/libQt5Test.so.5.*
}
do_install_append_morty() {
    	rm -f ${D}/${libdir}/qt5/plugins/platforms/libqlinuxfb.so*
    	rm -f ${D}/${libdir}/qt5/plugins/sqldrivers/libqsqlite.so*
    	rm -rf ${D}/${libdir}/qt5/plugins/sqldrivers
    	rm -f ${D}/${libdir}/qt5/plugins/bearer/libqconnmanbearer.so*
    	rm -f ${D}/${libdir}/qt5/plugins/bearer/libqnmbearer.so*
    	rm -f ${D}/${libdir}/qt5/plugins/platforminputcontexts/libibusplatforminputcontextplugin.so*
    	rm -f ${D}/${libdir}/qt5/plugins/platforminputcontexts/libmaliitplatforminputcontextplugin.so*
    	rm -f ${D}/${libdir}/qt5/plugins/accessible/libqtaccessiblewidgets.so
    	rm -rf ${D}/${libdir}/qt5/plugins/accessible
}
do_install_append_dunfell() {
    	rm -f ${D}/${libdir}/qt5/plugins/platforms/libqlinuxfb.so*
    	rm -f ${D}/${libdir}/qt5/plugins/sqldrivers/libqsqlite.so*
    	rm -rf ${D}/${libdir}/qt5/plugins/sqldrivers
    	rm -f ${D}/${libdir}/qt5/plugins/bearer/libqconnmanbearer.so*
    	rm -f ${D}/${libdir}/qt5/plugins/bearer/libqnmbearer.so*
    	rm -f ${D}/${libdir}/qt5/plugins/platforminputcontexts/libibusplatforminputcontextplugin.so*
    	rm -f ${D}/${libdir}/qt5/plugins/platforminputcontexts/libmaliitplatforminputcontextplugin.so*
    	rm -f ${D}/${libdir}/qt5/plugins/accessible/libqtaccessiblewidgets.so
    	rm -rf ${D}/${libdir}/qt5/plugins/accessible
        
        rm -f  ${D}/${datadir}/fonts/DejaVuSans-BoldOblique.ttf
        rm -f  ${D}/${datadir}/fonts/DejaVuSans-Oblique.ttf
}

FILES_${PN}-fonts-ttf-vera       = "${datadir}/fonts/Vera*.ttf"
FILES_${PN}-fonts-ttf-dejavu     = "${datadir}/fonts/DejaVu*.ttf"
FILES_${PN}-fonts-pfa            = "${datadir}/fonts/*.pfa"
FILES_${PN}-fonts-qpf            = "${datadir}/fonts/*.qpf*"
FILES_${PN}-fonts                = "${datadir}/fonts/README \
                                    ${datadir}/fonts/fontdir"

PACKAGES =. "\
    ${PN}-test \
    ${PN}-printsupport \
    ${PN}-sql \
    ${PN}-xml \
"

EXTRA_OECONF_remove = " --enable-nls"
FILES_${PN}-test_remove = "${libdir}/libQt5Test.so.5.*"
FILES_${PN}-printsupport = "${libdir}/libQt5PrintSupport.so.5.*"
FILES_${PN}-sql = "${libdir}/libQt5Sql.so.5.*"
FILES_${PN}-xml = "${libdir}/libQt5Xml.so.5.*"
FILES_${PN}_remove = " ${libdir}/libQt5Test.so.5.* \
                       ${libdir}/libQt5Xml.so.5.* \
                     "

INSANE_SKIP_${PN}-examples-dev = "dev-elf"
INSANE_SKIP_${PN}-examples_dunfell += "file-rdeps"
