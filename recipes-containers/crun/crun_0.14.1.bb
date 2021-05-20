SUMMARY = "A fast and lightweight fully featured OCI runtime and C library for running containers"

DESCRIPTION = "A fast and low-memory footprint OCI Container Runtime fully written in C.\
crun conforms to the OCI Container Runtime specifications"

HOMEPAGE = "https://github.com/containers/crun"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV="0.14.1"

SRC_URI = "gitsm://github.com/containers/crun \
           file://0001-container-truncate-pid-file-before-writing-it.patch \
"

SRCREV = "88886aef25302adfd40a9335372bbc2b970c8ae5"

DEPENDS += "yajl libseccomp libtool libcap"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit autotools-brokensep pkgconfig

do_configure_prepend () {
    cd ${S}
    ./autogen.sh
}

# Force bitbake to ensure libocispec has been compiled before compiling crun
# Fix random build failure due to race condition in Jenkins
do_compile_prepend() {
    cd ${S}/libocispec
    oe_runmake
    cd ${S}
}

# Don't need systemd integration, so disable it to remove dependency on libsystemd
EXTRA_OECONF = "--disable-systemd"
