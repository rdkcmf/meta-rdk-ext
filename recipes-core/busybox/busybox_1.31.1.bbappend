SRC_URI += " \
             file://0001-add-ENABLE_FEATURE_SYSTEMD-and-use-it-in-syslogd.patch \
             file://CVE-2018-1000500.patch \
           "
VERSION_PATCHES_append_client = " file://busybox-1.31-udhcp-trigger-milestones.patch"
