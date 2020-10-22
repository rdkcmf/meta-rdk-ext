FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

CXXFLAGS_append = " -Os "
SRC_URI += " \
             file://port_dibbler_patches_from_1_0_0_RC2.patch \
             file://DELIA-34037-Dibbler-client-crash-in-delete_radvd_conf.patch \
	     file://RDKB-Fix-BindReuse-Bug.patch "
SRC_URI_append_hybrid = " file://update_logging_path_hybrid_1_0_1.patch"
SRC_URI_append_client = " file://0001-RDK-32168-Set-default-log-path-for-dibbler-client.patch "

