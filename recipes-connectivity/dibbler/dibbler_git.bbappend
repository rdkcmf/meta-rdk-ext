FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

CXXFLAGS_append = " -Os "
SRC_URI += "file://DELIA-34037-Dibbler-client-crash-in-delete_radvd_conf.patch \
            file://RDKB-Fix-BindReuse-Bug.patch \
	    file://0002-port-dibbler-patches-from-1.0.0_RC2-for-dunfell.patch \
            "
SRC_URI_append_hybrid = " file://0004-update_logging_path_hybrid_1_0_1_for_dunfell.patch "

