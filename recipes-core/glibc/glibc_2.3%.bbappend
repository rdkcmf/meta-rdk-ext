# provided by libnsl2
do_install_append_class-nativesdk() {
    rm -f ${D}${includedir}/rpcsvc/yppasswd.*
}