FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
        file://libpng-1.6.37-apng.patch \
        "
