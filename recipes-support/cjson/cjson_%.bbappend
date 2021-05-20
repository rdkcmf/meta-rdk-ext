do_install_append_dunfell() {
        # Create symlink to support meta-rdk components which expect cJSON.h to
        # be found in the toplevel sysroot ${includedir} rather than within the
        # cjson subdirectory. Fixme: The real solution would be to fix those
        # recipes and then remove this symlink.
        ln -s cjson/cJSON.h ${D}${includedir}/cJSON.h
}

