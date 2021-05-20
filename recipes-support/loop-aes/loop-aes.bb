SUMMARY = "Loop-aes - replacement for loop driver that can do aes encryption"
DESCRIPTION = "Fast and transparent file system and swap encryption package for \
               linux. No source code changes to linux kernel. Works with 3.x,\
               2.6, 2.4, 2.2 and 2.0 kernels."
HOMEPAGE = "http://sourceforge.net/projects/loop-aes/"
LICENSE = "GPLv2 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://loop-aes-keygen;beginline=5;endline=16;md5=fea1e40a702b207386cd311896610b12 \
                    file://aes-x86.S;beginline=7;endline=22;md5=ef85da542737e6d1610d12186407ce88 \
"

SRC_URI = "http://loop-aes.sourceforge.net/loop-AES/loop-AES-v3.7a.tar.bz2"
SRC_URI[md5sum] = "f267cb2108ee94b09a30b2fc7e292104"
SRC_URI[sha256sum] = "9f0ac940ba478c9f47592450a6788d4b15bfb4e90e1f05ba32c36cbf46074d36"

inherit module
EXTRA_OEMAKE = "-C ${WORKDIR}/loop-AES-v3.7a  LINUX_SOURCE=${STAGING_KERNEL_DIR} MODINST=n"

do_install () {
     install -d ${D}/loop-aes
     install -m0655 ${WORKDIR}/loop-AES-v3.7a/loop.ko ${D}/loop-aes
}

FILES_${PN} += "loop-aes/loop.ko"
do_populate_sysroot[noexec] = "1"
INHIBIT_PACKAGE_STRIP = "1"
