
# Disable PHP features not required for RDK-B

PACKAGECONFIG_remove = "mysql"
PACKAGECONFIG_remove = "sqlite3"
PACKAGECONFIG_remove = "imap"

EXTRA_OECONF += " \
    --with-curl=${STAGING_LIBDIR}/.. \
    --with-openssl=${STAGING_INCDIR}/.. \
"
DEPENDS_append = " openssl curl"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://md4-remove.patch"

CACHED_CONFIGUREVARS_remove_dunfell = "ac_cv_func_dlopen=no"
CFLAGS_append_dunfell = " -DHAVE_LIBDL "
LDFLAGS_append_dunfell = " -ldl "
