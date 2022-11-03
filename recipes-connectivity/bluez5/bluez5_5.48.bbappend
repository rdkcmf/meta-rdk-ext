FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Fix-race-issue-with-tools-directory.patch \
            file://CVE-2019-8922.patch \
            file://CVE-2020-27153.patch \
            file://CVE-2022-0204.patch \
            file://CVE-2020-0556.patch \
            file://0002-Fixing-connection-failure-due-to-CVE-2020-0556.patch \
            file://CVE-2018-10910.patch \
"
