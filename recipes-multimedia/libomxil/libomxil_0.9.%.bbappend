FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
        file://omx_base_component_buffersize.patch \
        "

