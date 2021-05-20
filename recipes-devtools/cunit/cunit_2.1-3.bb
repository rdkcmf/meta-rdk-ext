DESCRIPTION = "CUnit is a C framework for unit testing. Test output supports comandline and GUI results reporting"
HOMEPAGE = "http://cunit.sourceforge.net"
LICENSE = "LGPL-2.0"
SECTION = "libs"
LIC_FILES_CHKSUM = "file://COPYING;md5=7734aa853b85d6f935466f081490ddbb"

S = "${WORKDIR}/CUnit-${PV}"

SRC_URI = "${SOURCEFORGE_MIRROR}/project/cunit/CUnit/${PV}/CUnit-${PV}.tar.bz2 \
    file://fixup-install-docdir.patch"
SRC_URI[md5sum] = "b5f1a9f6093869c070c6e4a9450cc10c"
SRC_URI[sha256sum] = "f5b29137f845bb08b77ec60584fdb728b4e58f1023e6f249a464efa49a40f214"

inherit autotools-brokensep

# ----------------------------------------------------------------------------
# BEGIN : Backport of "remove-libtool" class from OE 2.1
# ----------------------------------------------------------------------------
# Postprocess ${D} to remove all .la files installed by libtool, so that they
# are absent from both the sysroots and target packages. Avoids triggering QA
# staging "workdir" errors when building the meta-rdk cunit recipe with OE 2.2.
# In the upstream cunit recipe in meta-oe the same postprocessing is done by
# inheritting the "remove-libtool" class, however since that class is only
# available from OE 2.1 onwards, provide the same logic directly in the cunit
# recipe to be compatible with OE 1.6.
# ----------------------------------------------------------------------------

remove_libtool_la() {
	find "${D}" -ignore_readdir_race -name "*.la" -delete
}

do_install[postfuncs] += "remove_libtool_la"

# ----------------------------------------------------------------------------
# END : Backport of "remove-libtool" class from OE 2.1
# ----------------------------------------------------------------------------

EXTRA_OECONF = "--enable-memtrace --enable-automated --enable-basic --enable-console"

FILES_${PN}-dev += "${datadir}/CUnit"
FILES_${PN}-doc += "${docdir}"
