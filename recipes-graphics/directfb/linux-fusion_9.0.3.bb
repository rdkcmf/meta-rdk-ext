SUMMARY = "DirectFB Linux-fusion kernel module"

SRC_URI = " \
    http://directfb.org/downloads/Core/linux-fusion/linux-fusion-${PV}.tar.gz \
    file://0001-linux-fusion-support-kernel-4-and-beyond.patch \
    file://0002-one-udp-support-kernel-4-and-beyond.patch \
    file://0003-Fix-Fusion-Unknown-Symbol-tasklist_lock.patch \
"
SRC_URI[md5sum] = "f42a089ea3f649b9ab3a98d812ce1821"
SRC_URI[sha256sum] = "ce9ce06293ccabb139ff972f7d7e44308bb92e7979f9c56b973da34c4dd3506e"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=0ad6a1d63862749c355c08ca3fd0c39a"

inherit module

EXTRA_OEMAKE += " \
    SYSROOT=${STAGING_KERNEL_DIR} \
    KERNEL_BUILD=${STAGING_KERNEL_DIR} \
"

module_do_compile_prepend () {
 cd ${S}
}

module_do_install_prepend () {
 cd ${S}
}

module_do_install_prepend () {
    # module_do_install in module.bbclass expects Module.symvers to be present in B,
    # but it is currently present in the individual driver repositories (linux/drivers/char/fusion/ and one/)
    # So give it what it expects, to pass installation phase
    cp ${B}/linux/drivers/char/fusion/Module.symvers ${B}
}

module_do_install_append () {
        oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" \
            CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
            headers_install
}

KERNEL_MODULE_AUTOLOAD += "fusion"

