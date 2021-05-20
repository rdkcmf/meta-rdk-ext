SUMMARY = "A stacked cryptographic filesystem for Linux"
DESCRIPTION = "eCryptfs stores cryptographic metadata in the header of each \
file written, so that encrypted files can be copied between hosts; the file \
will be decrypted with the proper key in the Linux kernel keyring"
SECTION = "base"
LICENSE = "GPLv2"
DEPENDS = "intltool-native keyutils libgcrypt libpam glib-2.0-native openssl"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "https://launchpad.net/ecryptfs/trunk/${PV}/+download/${BPN}_${PV}.orig.tar.gz \
           file://ecryptfs-fix-disable-nss.patch \
           "
SRC_URI_append_dunfell = "\ 
           file://0001-ecryptfs-patch-for-openssl-1.x.patch \
           "
SRC_URI[md5sum] = "bff8052636f6be642f15c6be45a14ea3"
SRC_URI[sha256sum] = "173e0add31e898789076103723894964ce474233988ef8d3309021bad8a7b6b4"

inherit pkgconfig autotools systemd

SYSTEMD_PACKAGES = "${PN}"

EXTRA_OECONF += "--disable-nss --disable-pywrap --enable-openssl --prefix=/ --sbindir=/sbin --datarootdir=/usr/share"
EXTRA_OEMAKE += "'CFLAGS+= -lgcrypt '"

FILES_${PN} += " \
               ${libdir}/security/pam_ecryptfs.so \
               ${libdir}/libecryptfs.so.1 \
               ${libdir}/libecryptfs.so.1.0.0 \
               ${libdir}/ecryptfs/libecryptfs_key_mod_openssl.so \
               ${libdir}/ecryptfs/libecryptfs_key_mod_passphrase.so \
               "

FILES_${PN}-doc += " \
                   /share/locale/* \
                   "

FILES_${PN}-dbg += "${libdir}/ecryptfs/.debug \
                    ${libdir}/security/.debug \
                   "
do_install_append() {
    rm -rf ${D}${bindir}/ecryptfsd
    rm -rf ${D}${bindir}/ecryptfs-find
    rm -rf ${D}${bindir}/ecryptfs-insert-wrapped-passphrase-into-keyring
    rm -rf ${D}${bindir}/ecryptfs-manager
    rm -rf ${D}${bindir}/ecryptfs-migrate-home
    rm -rf ${D}${bindir}/ecryptfs-mount-private
    rm -rf ${D}${bindir}/ecryptfs-recover-private
    rm -rf ${D}${bindir}/ecryptfs-rewrap-passphrase
    rm -rf ${D}${bindir}/ecryptfs-rewrite-file
    rm -rf ${D}${bindir}/ecryptfs-setup-private
    rm -rf ${D}${bindir}/ecryptfs-setup-swap
    rm -rf ${D}${bindir}/ecryptfs-stat
    rm -rf ${D}${bindir}/ecryptfs-umount-private
    rm -rf ${D}${bindir}/ecryptfs-unwrap-passphrase
    rm -rf ${D}${bindir}/ecryptfs-verify
    rm -rf ${D}${bindir}/ecryptfs-wrap-passphrase
}

