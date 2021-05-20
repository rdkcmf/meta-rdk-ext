FILESEXTRAPATHS_prepend := "${THISDIR}/kernel_4.1:"

SRC_URI_append = "\
                  file://ambient-capabilities-kernel-4.1.38.patch \
                 "
