FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
#Enforce stunnel to link it with openssl-1.0.x for dunfell build. 
SRC_URI_append_dunfell = " file://0001-enforce-stunnel-link-with-openssl-1.0.2o.patch"
DEPENDS_remove_dunfell  = "openssl"
DEPENDS_append_dunfell  = " openssl-1.0.2o"
CFLAGS_append_dunfell = " -I${STAGING_INCDIR}/openssl-1.0.2o"
LDFLAGS_append_dunfell = " -L${STAGING_LIBDIR}/openssl-1.0.2o -lcrypto-1.0.2o -lssl-1.0.2o"

