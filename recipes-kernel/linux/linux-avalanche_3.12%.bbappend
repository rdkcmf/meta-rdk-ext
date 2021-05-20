FILESEXTRAPATHS_prepend := "${THISDIR}/kernel_3.12:"
SRC_URI_append = "\
		  file://ambient-capabilities-kernel-3.12.patch \
                  file://fix-typo-security-task-prctl.patch \
                 "
