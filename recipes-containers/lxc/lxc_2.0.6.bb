DESCRIPTION = "lxc aims to use these new functionnalities to provide an userspace container object"
SECTION = "console/utils"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "libxml2 libcap "
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'seccomp', 'libseccomp', '', d)}"

# if you want to run non-secure containers (running under user root) then
# remove patch "file://0001-drop-privileges-after-fork.patch "below.
# Or else lxc will want to drop to user "lxc" in this patch.
SRC_URI = "http://linuxcontainers.org/downloads/${BPN}/${BPN}-${PV}.tar.gz \
           file://0001-drop-privileges.patch \
           file://0002-drop-privileges.patch \
           file://0001-drop-privileges-after-fork.patch \
           file://0003-disable-cmdline-options-to-harden-lxc-attach.patch;striplevel=1 \
           file://0005-lxc-execute-return-exitcode.patch;striplevel=1 \
           file://0006-catch-signal-return-exitcode.patch;striplevel=1 \
           file://0007-OMWHI-864-Allow_pass_subdirs_in_dev.patch \
           file://0008-cgroup-hierarchy-mount-access-for-others.patch \
           file://0009-ensure-cgroup-are-cleaned-up.patch \
           file://0010-unify-env-setup.patch \
           file://0011-handle-mutliple-ld-preload-in-conf-file.patch \
"

SRC_URI[md5sum] = "5fd4b7af8026e8ae20b3065ee18fe974"
SRC_URI[sha256sum] = "7c292cd0055dac1a0e6fbb6a7740fd12b6ffb204603c198faf37c11c9d6dcd7a"

REQUIRED_DISTRO_FEATURES = "lxc-secure-containers"
inherit autotools pkgconfig distro_features_check

CACHED_CONFIGUREVARS += "ac_cv_lib_gnutls_gnutls_hash_fast=no"

EXTRA_OECONF = " \
    --enable-capabilities \
    --disable-rpath \
    --disable-doc \
    --disable-api-docs \
    --disable-apparmor \
    --disable-selinux \
    --disable-cgmanager \
    --disable-examples \
    --disable-python \
    --disable-lua \
    --disable-bash \
    --disable-tests \
    --disable-configpath-log \
    --with-distro=unknown \
"
EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'seccomp', '--enable-seccomp', '--disable-seccomp', d)}"

# enable non-secure lxc-attach only in debug
EXTRA_OEMAKE += "${@bb.utils.contains('MACHINE_FEATURES', 'debug', 'CFLAGS=-DNON_SECURE_LXC', '', d)}"

RDEPENDS_${PN} = "${@bb.utils.contains('MACHINE_FEATURES', 'debug', 'libcap-bin bridge-utils', '', d)}"

do_install_append() {
    # Remove unused lxc tools from release and production build
    if ${@bb.utils.contains('MACHINE_FEATURES', 'debug', 'false', 'true', d)}; then
        rm -f ${D}${bindir}/lxc-cgroup
        rm -f ${D}${bindir}/lxc-checkpoint
        rm -f ${D}${bindir}/lxc-console
        rm -f ${D}${bindir}/lxc-create
        rm -f ${D}${bindir}/lxc-device
        rm -f ${D}${bindir}/lxc-freeze
        rm -f ${D}${bindir}/lxc-ls
        rm -f ${D}${bindir}/lxc-snapshot
        rm -f ${D}${bindir}/lxc-unfreeze
        rm -f ${D}${bindir}/lxc-usernsexec
        rm -f ${D}${bindir}/lxc-autostart
        rm -f ${D}${bindir}/lxc-checkconfig
        rm -f ${D}${bindir}/lxc-config
        rm -f ${D}${bindir}/lxc-copy
        rm -f ${D}${bindir}/lxc-destroy
        rm -f ${D}${bindir}/lxc-info
        rm -f ${D}${bindir}/lxc-monitor
        rm -f ${D}${bindir}/lxc-top
        rm -f ${D}${bindir}/lxc-unshare

        rm -f ${D}${libexecdir}/lxc/lxc-user-nic
        rm -f ${D}${libexecdir}/lxc/lxc-containers
        rm -f ${D}${libexecdir}/lxc/lxc-net
    fi

    rm -rf ${D}/var/cache/lxc
}

FILES_${PN}-dev += "${datadir}"
FILES_${PN}-dev += "${includedir}"
FILES_${PN}-doc = "${mandir} ${infodir}"
FILES_${PN} += "${libdir}/python3*"
FILES_${PN}-dbg += "${libexecdir}/lxc/.debug"
FILES_${PN}-dbg += "${libexecdir}/lxc/hooks/.debug"
FILES_${PN}-dbg += "${libexecdir}/lxc/hooks/.debug/unmount-namespace"
