require openssl-0.9.inc

SRC_URI[src.md5sum] = "c69a4a679233f7df189e1ad6659511ec"
SRC_URI[src.sha256sum] = "d5245a29128984192acc5b1fc01e37429b7a01c53cadcb2645e546718b300edb"



SRC_URI += "file://find.pl \
            file://no-rpath.patch \
            file://configure-targets.patch \
            file://debian.patch \
            file://oe-ldflags.patch \
            file://openssl-util-perlpath.pl-cwd.patch \
            file://comcast.patch"

SRC_URI_append_dunfell = " file://0001-Changes-for-adding-find.pl-in-search-path.patch"

#
PARALLEL_MAKE = ""
PARALLEL_MAKEINST = ""
INSANE_SKIP += "ldflags"
TARGET_CC_ARCH += "${LDFLAGS}"

do_configure_prepend() {
	cp ${WORKDIR}/find.pl ${S}/util/find.pl
}

do_install_append_dunfell() {
  rm -f ${D}${bindir}/c_rehash
}
