SUMMARY = "A fast and lightweight fully featured OCI runtime and C library for running containers"

DESCRIPTION = "A fast and low-memory footprint OCI Container Runtime fully written in C.\
crun conforms to the OCI Container Runtime specifications"

HOMEPAGE = "https://github.com/containers/crun"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "gitsm://github.com/containers/crun;branch=main \
           file://0001-Fix-compilation-error.patch \
           file://0002-cgroup-destroy-hang.patch"

SRCREV = "f6fbc8f840df1a414f31a60953ae514fa497c748"

DEPENDS += "yajl libseccomp libtool libcap"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'RDKTV_CHECKPOINT_RESTORE', ' criu', '', d)}"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit autotools-brokensep pkgconfig

do_configure_prepend () {
    cd ${S}
    ./autogen.sh
}

# Don't need systemd integration, so disable it to remove dependency on libsystemd
EXTRA_OECONF = "--disable-systemd"
