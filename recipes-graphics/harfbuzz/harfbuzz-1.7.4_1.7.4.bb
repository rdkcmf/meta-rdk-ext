SUMMARY = "Text shaping library"
DESCRIPTION = "HarfBuzz is an OpenType text shaping engine."
HOMEPAGE = "http://www.freedesktop.org/wiki/Software/HarfBuzz"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=HarfBuzz"
SECTION = "libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e021dd6dda6ff1e6b1044002fc662b9b \
                    file://src/hb-ucdn/COPYING;md5=994ba0f1295f15b4bda4999a5bbeddef \
"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
DEPENDS = "glib-2.0 cairo fontconfig freetype"

SRC_URI = "http://www.freedesktop.org/software/harfbuzz/release/${BPN}.tar.bz2"
SRC_URI[md5sum] = "c7476d8b989869d8b95b37fa53add6bf"
SRC_URI[sha256sum] = "b5d6ac8415f97f3540d73f3f91c41c5c10f8a4d76350f11a7184062aae88ac0b"

SRC_URI_append = " file://0001-SERXIONE-219-Harfbuzz-1.7.4-changes.patch"
S = "${WORKDIR}/harfbuzz-${PV}"

inherit autotools pkgconfig lib_package gtk-doc

PACKAGECONFIG ??= "icu"
PACKAGECONFIG[icu] = "--with-icu,--without-icu,icu"
EXTRA_OECONF = " \
    --with-cairo \
    --with-fontconfig \
    --with-freetype \
    --with-glib \
    --without-graphite2 \
"

PACKAGES =+ "${PN}-icu ${PN}-icu-dev"

PACKAGECONFIG_append += " icu"
EXTRA_OECONF_remove = "--with-cairo"
EXTRA_OECONF_append  = " --with-cairo=no"

do_install_append () {
        install -d ${D}${bindir}/harfbuzz-1.7.4
        mv ${D}${bindir}/hb* ${D}${bindir}/harfbuzz-1.7.4/
        mv ${D}${includedir}/harfbuzz ${D}${includedir}/harfbuzz-1.7.4
        mv ${D}${libdir}/pkgconfig/harfbuzz-icu.pc ${D}${libdir}/pkgconfig/harfbuzz-1.7.4-icu.pc
        mv ${D}${libdir}/pkgconfig/harfbuzz.pc ${D}${libdir}/pkgconfig/harfbuzz-1.7.4.pc

        sed -i '/^includedir=/ s%$%\/harfbuzz-1.7.4%' ${D}/${libdir}/pkgconfig/*.pc
        sed -i s%\-lharfbuzz%\-lharfbuzz-1.7.4% ${D}/${libdir}/pkgconfig/harfbuzz-1.7.4.pc
        sed -i s%\-lharfbuzz-icu%\-lharfbuzz-1.7.4-icu% ${D}/${libdir}/pkgconfig/harfbuzz-1.7.4-icu.pc
}

FILES_${PN}-icu = "${libdir}/libharfbuzz-1.7.4-icu.so.* \
                   ${libdir}/libharfbuzz-icu.so.* \
                   "
FILES_${PN}-icu-dbg = "${libdir}/.debug/libharfbuzz-1.7.4-icu.so*"
FILES_${PN}-icu-dev = "${libdir}/libharfbuzz-1.7.4-icu.la \
                       ${libdir}/libharfbuzz-1.7.4-icu.so \
                       ${libdir}/pkgconfig/harfbuzz-1.7.4-icu.pc \
"

BBCLASSEXTEND = "native"
