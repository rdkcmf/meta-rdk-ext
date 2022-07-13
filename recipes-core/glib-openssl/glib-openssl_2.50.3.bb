SUMMARY = "GLib OpenSSL extension"
DESCRIPTION = "Network-related giomodule for glib using openssl."
HOMEPAGE = "http://git.gnome.org/browse/glib-openssl/"
BUGTRACKER = "http://bugzilla.gnome.org"

LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SECTION = "libs"
DEPENDS = "glib-2.0 openssl intltool-native"

GNOME_COMPRESS_TYPE = "xz"

SRC_URI[archive.md5sum] = "bd4746fcd00bf338af538bd765413a5b"
SRC_URI[archive.sha256sum] = "0211c118b86aec228d2b7d2606bba9637d5bb5d60694cc7ccb6d2920f02866bc"

PACKAGECONFIG ??= "ca-certificates"

PACKAGECONFIG[ca-certificates] = "--with-ca-certificates=${sysconfdir}/ssl/certs/ca-certificates.crt,--without-ca-certificates"

inherit gnomebase gettext

FILES_${PN} += "${libdir}/gio/modules/libgio*.so ${datadir}/dbus-1/services/"
FILES_${PN}-dbg += "${libdir}/gio/modules/.debug/"
FILES_${PN}-dev += "${libdir}/gio/modules/libgio*.la"
FILES_${PN}-staticdev += "${libdir}/gio/modules/libgio*.a"

SRC_URI += "\
    file://find_CA_bundle_file_in_configure.patch \
    file://allow_underscore_in_hostname.patch \
    file://relax_read_error_handling.patch \
    file://certificate_leak_fix.patch \
    file://only_initialize_dataindex_once.patch \
    file://OCSP_stapling_capabilities.patch \
    file://update_OCSP_stapling_capabilities_patch_for_c89_compilers.patch \
    file://force_tls1_2.patch \
    file://0001-XRE-14265-request-client-cert-support.patch \
    file://fix_dates_check.patch \
    file://Enable-OCSP-by-default.patch \
"
