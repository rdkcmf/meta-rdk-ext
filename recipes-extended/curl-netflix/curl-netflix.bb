SUMMARY = "Command line tool and library for client-side URL transfers"
HOMEPAGE = "http://curl.haxx.se/"
BUGTRACKER = "http://curl.haxx.se/mail/list.cgi?list=curl-tracker"
SECTION = "console/network"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;beginline=8;md5=3a34942f4ae3fbf1a303160714e664ac"
PV = "7.59.0"
S = "${WORKDIR}/curl-${PV}/"

SRC_URI = "http://curl.haxx.se/download/curl-${PV}.tar.bz2 \
           file://0001-replace-krb5-config-with-pkg-config.patch \
"
SRC_URI_append_dunfell = "${@bb.utils.contains('PREFERRED_VERSION_netflix', '5.3%','', bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', '', ' file://0001-Use-openssl1.0.2o-for-curl-netflix.patch',d),d)}"

SRC_URI[md5sum] = "a2192804f7c2636a09320416afcf888e"
SRC_URI[sha256sum] = "b5920ffd6a8c95585fb95070e0ced38322790cb335c39d0dab852d12e157b5a0"

CVE_PRODUCT = "libcurl"
inherit autotools pkgconfig multilib_header

PACKAGECONFIG ??= "${@bb.utils.contains("DISTRO_FEATURES", "ipv6", "ipv6", "", d)} gnutls proxy c-ares zlib"
PACKAGECONFIG_class-native = "ipv6 proxy ssl c-ares zlib"
PACKAGECONFIG_class-nativesdk = "ipv6 proxy ssl c-ares zlib"

# 'ares' and 'threaded-resolver' are mutually exclusive
PACKAGECONFIG[c-ares] = "--enable-ares,--disable-ares,c-ares"
PACKAGECONFIG[dict] = "--enable-dict,--disable-dict,"
PACKAGECONFIG[gnutls] = "--with-gnutls,--without-gnutls,gnutls"
PACKAGECONFIG[gopher] = "--enable-gopher,--disable-gopher,"
PACKAGECONFIG[imap] = "--enable-imap,--disable-imap,"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,"
PACKAGECONFIG[krb5] = "--with-gssapi,--without-gssapi,krb5"
PACKAGECONFIG[ldap] = "--enable-ldap,--disable-ldap,"
PACKAGECONFIG[ldaps] = "--enable-ldaps,--disable-ldaps,"
PACKAGECONFIG[libidn] = "--with-libidn2,--without-libidn2,libidn2"
PACKAGECONFIG[libssh2] = "--with-libssh2,--without-libssh2,libssh2"
PACKAGECONFIG[nghttp2] = "--with-nghttp2,--without-nghttp2,nghttp2"
PACKAGECONFIG[pop3] = "--enable-pop3,--disable-pop3,"
PACKAGECONFIG[proxy] = "--enable-proxy,--disable-proxy,"
PACKAGECONFIG[rtmpdump] = "--with-librtmp,--without-librtmp,rtmpdump"
PACKAGECONFIG[rtsp] = "--enable-rtsp,--disable-rtsp,"
PACKAGECONFIG[smb] = "--enable-smb,--disable-smb,"
PACKAGECONFIG[smtp] = "--enable-smtp,--disable-smtp,"
PACKAGECONFIG[ssl] = "--with-ssl --with-random=/dev/urandom,--without-ssl,openssl"
PACKAGECONFIG[telnet] = "--enable-telnet,--disable-telnet,"
PACKAGECONFIG[tftp] = "--enable-tftp,--disable-tftp,"
PACKAGECONFIG[threaded-resolver] = "--enable-threaded-resolver,--disable-threaded-resolver"
PACKAGECONFIG[zlib] = "--with-zlib=${STAGING_LIBDIR}/../,--without-zlib,zlib"
PACKAGECONFIG[brotli] = "--with-brotli,--without-brotli,brotli"

EXTRA_OECONF = " \
    --enable-crypto-auth \
    --with-ca-bundle=${sysconfdir}/ssl/certs/ca-certificates.crt \
    --without-libmetalink \
    --without-libpsl \
    --enable-optimize \
    --disable-debug \
    --disable-ftp \
    --disable-file \
    --disable-ldap \
    --disable-ldaps \
    --disable-rtsp \
    --disable-dict \
    --disable-telnet \
    --disable-tftp \
    --disable-gopher \
    --disable-pop3 \
    --disable-imap \
    --disable-smtp \ 
    --without-libidn \
"

do_install(){
 install -d ${D}/usr/include/netflix
 install -d ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/curl.h          ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/curlver.h       ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/easy.h          ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/mprintf.h       ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/multi.h         ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/stdcheaders.h   ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/system.h        ${D}/usr/include/netflix/curl
 install -m644 ${S}include/curl/typecheck-gcc.h ${D}/usr/include/netflix/curl
 install -d ${D}/usr/lib/netflix
 install -m644 ${B}/lib/.libs/libcurl.a  ${D}/usr/lib/netflix
}

PACKAGES =+ "lib${BPN}"
PACKAGES =+ "lib${BPN}-dev lib${BPN}-staticdev"

#FILES_lib${BPN} = "${libdir}/lib*.so.*"


RRECOMMENDS_lib${BPN} += "ca-certificates"

FILES_${PN} += "${datadir}/zsh"

BBCLASSEXTEND = "native nativesdk"


CURLGNUTLS = "--without-gnutls --with-ssl"
DEPENDS += " openssl c-ares"

#Enforce to use openssl_1.0.2o version dunfell build with Netflix 5.1
DEPENDS_append_dunfell = "${@bb.utils.contains('PREFERRED_VERSION_netflix', '5.3%','', bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', '', ' openssl-1.0.2o', d), d)}"
DEPENDS_remove_dunfell = "${@bb.utils.contains('PREFERRED_VERSION_netflix', '5.3%','', bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', '', 'openssl', d), d)}"
CFLAGS_append_dunfell = "${@bb.utils.contains('PREFERRED_VERSION_netflix', '5.3%','', bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', '', ' -I${STAGING_INCDIR}/openssl-1.0.2o', d), d)}"
LDFLAGS_append_dunfell = "${@bb.utils.contains('PREFERRED_VERSION_netflix', '5.3%','', bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', '', ' -L${STAGING_LIBDIR}/openssl-1.0.2o', d), d)}"
LIBS_append_dunfell  =  "${@bb.utils.contains('PREFERRED_VERSION_netflix', '5.3%', '', bb.utils.contains('DISTRO_FEATURES', 'ssl-1.1.1', '', ' -lcrypto-1.0.2o -lssl-1.0.2o', d), d)}"
CFLAGS += " -fPIC"

# Latest curl recipe 7.50.1 version comes with Yocto 2.2 is changed to use PACKAGECONFIG
PACKAGECONFIG_remove_class-target = "gnutls"
PACKAGECONFIG_append_class-target = " ssl"

# see https://lists.yoctoproject.org/pipermail/poky/2013-December/009435.html
# We should ideally drop ac_cv_sizeof_off_t from site files but until then
EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'largefile', 'ac_cv_sizeof_off_t=8', '', d)}"

PACKAGECONFIG_append = " ipv6 "
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,"

PRIVATE_LIBS = "libcurl.a libcurl.la"
FILES_lib${BPN}-dev = "${includedir}/netflix \
                      ${libdir}/netflix/lib*.la \
                      ${libdir}/netflix/pkgconfig"

FILES_lib${BPN}-staticdev = "${libdir}/netflix/lib*.a"

ALLOW_EMPTY_${PN} = "1"

