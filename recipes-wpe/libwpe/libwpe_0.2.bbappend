do_install_append_dunfell () {
    #install header files
    mkdir -p ${D}${includedir}/wpe
    install -m 0644 ${D}${includedir}/wpe-0.2/wpe/*.h ${D}${includedir}/wpe/
}
