SRCREV = "ca1c74d67dd115d059a875150e10b8560a9c35a8"

PV = "0.9.33+git${SRCPV}"

FILESEXTRAPATHS =. "${FILE_DIRNAME}/uclibc-git:"

SRC_URI = "git://uclibc.org/uClibc.git;branch=master \
        file://uClibc.machine \
        file://uClibc.distro \
        file://obstack.cfg \
        file://locale.cfg \
        file://0001-Disable-lrount_tes-function.patch \
        file://0002-Add-implementation-for-copysignl-for-ppc.patch \
        file://0003-Add-argp-implementation.patch \
        file://0004-Dont-support-localised-optimizations-this-helps-to-h.patch \
        file://0005-Always-use-O2-for-compiling-fork.c.patch \
        file://0006-ldso-limited-support-for-ORIGIN-in-rpath.patch \
        file://0007-nptl-atfork-Hide-pthread_atfork-in-shared-versions.patch \
        file://0001-gcc5-optimizes-away-the-write-only-static-functions-.patch \
        file://0001-fcntl-Add-AT_EMPTY_PATH-for-all-and-O_PATH-for-arm.patch \
        file://0001-wire-in-syncfs.patch \
        file://add_O_PATH.patch \
        file://0030-arm-add-RESET_PID-in-the-clone-impl.patch \
        file://0160-libc-fix-sign-extension-in-fallocate.patch \
        file://0161-libc-posix_fallocate-must-return-an-error-number-on-.patch \
        file://0171-confdata-fix-invalid-write.patch \
        file://libc-stdlib-canonicalize_file_name-memory-leak.patch \
"

S = "${WORKDIR}/git"
