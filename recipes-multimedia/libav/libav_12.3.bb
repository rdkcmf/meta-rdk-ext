require libav_12.3.inc

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004"

SRC_URI = "https://libav.org/releases/libav-12.3.tar.xz"

SRC_URI[md5sum] = "753ec26481b0582eb737383bd8a350a5"
SRC_URI[sha256sum] = "6893cdbd7bc4b62f5d8fd6593c8e0a62babb53e323fbc7124db3658d04ab443b"

inherit autotools pkgconfig

do_rm_work() {
}

