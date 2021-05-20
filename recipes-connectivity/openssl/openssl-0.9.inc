DESCRIPTION = "Secure Socket Layer (SSL) binary and related cryptographic tools."
HOMEPAGE = "http://www.openssl.org/"
LICENSE = "openssl"
SECTION = "libs/network"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f9a8f968107345e0b75aa8c2ecaa7ec8"


SRC_URI = "https://www.openssl.org/source/old/0.9.x/openssl-${PV}.tar.gz;name=src"
S = "${WORKDIR}/openssl-${PV}"

inherit pkgconfig siteinfo multilib_header ptest

DEPENDS = "perl-native-runtime"
DEPENDS_remove_morty = " perl-native-runtime"
DEPENDS_append_morty = " hostperl-runtime-native"
DEPENDS_dunfell = " hostperl-runtime-native"
DEPENDS_append_class-target = " openssl-native"

AR_append = " r"
AR_remove_morty = " r"
AR_remove_dunfell = " r"
CFLAG = "${@oe.utils.conditional('SITEINFO_ENDIANESS', 'le', '-DL_ENDIAN', '-DB_ENDIAN', d)} \
	-DTERMIO ${FULL_OPTIMIZATION} -Wall"

# -02 does not work on mipsel: ssh hangs when it tries to read /dev/urandom
CFLAG_mtx-1 := "${@'${CFLAG}'.replace('-O2', '')}"
CFLAG_mtx-2 := "${@'${CFLAG}'.replace('-O2', '')}"

export DIRS = "crypto ssl apps"
export EX_LIBS = "-lgcc -ldl"
export AS = "${CC} -c"

do_configure () {
	cd util
	perl perlpath.pl ${bindir}
	cd ..
	ln -sf apps/openssl.pod crypto/crypto.pod ssl/ssl.pod doc/

	os=${HOST_OS}
	if [ "x$os" = "xlinux-uclibc" ]; then
		os=linux
	elif [ "x$os" = "xlinux-uclibceabi" ]; then
		os=linux
	elif [ "x$os" = "xlinux-gnueabi" ]; then
		os=linux
	fi
	target="$os-${HOST_ARCH}"
	case $target in
	linux-arm)
		target=linux-elf-arm
		;;
	linux-armeb)
		target=linux-elf-armeb
		;;
	linux-sh3)
		target=debian-sh3
		;;
	linux-sh4)
		target=debian-sh4
		;;
	linux-i486)
		target=debian-i386-i486
		;;
	linux-i586 | linux-viac3)
		target=debian-i386-i586
		;;
	linux-i686)
		target=debian-i386-i686/cmov
		;;
	linux-mips)
		target=debian-mips
		;;
	linux-mipsel)
		target=debian-mipsel
		;;
	linux-powerpc)
		target=linux-ppc
		;;
	linux-gnuspe-powerpc)
		target=linux-ppc
		;;
	linux-supersparc)
		target=linux-sparcv8
		;;
	linux-sparc)
		target=linux-sparcv8
		;;
	esac
	# inject machine-specific flags
	sed -i -e "s|^\(\"$target\",\s*\"[^:]\+\):\([^:]\+\)|\1:${CFLAG}|g" Configure
        useprefix=${prefix}
        if [ "x$useprefix" = "x" ]; then
                useprefix=/
        fi
	perl ./Configure shared --prefix=$useprefix --openssldir=${libdir}/ssl  $target
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${libdir}
	install -d ${D}${bindir}
	install -d ${D}${includedir}/openssl-0.9

	oe_runmake INSTALL_PREFIX="${D}" install_sw
	# On x86_64, move lib/* to lib64
	if [ "${libdir}" != "${prefix}/lib" ]
	then
		install -d ${D}${libdir} ${D}${libdir}/pkgconfig
		mv ${D}${prefix}/lib/lib* ${D}${libdir}
		mv ${D}${prefix}/lib/pkgconfig/*.pc ${D}${libdir}/pkgconfig
	fi

	oe_libinstall -so libcrypto ${D}${libdir}
	oe_libinstall -so libssl ${D}${libdir}
	install -m 0664 ${S}/e_os.h ${D}${includedir}/openssl
	sed -i '/^includedir=/ s%$%\/openssl-0.9%' ${D}/${libdir}/pkgconfig/*.pc
	sed -i s%\-lcrypto%\-lcrypto-0.9% ${D}/${libdir}/pkgconfig/*.pc
	sed -i s%\-lssl%\-lssl-0.9% ${D}/${libdir}/pkgconfig/*.pc
	mv ${D}${includedir}/openssl ${D}${includedir}/openssl-0.9
	mv ${D}${bindir}/openssl ${D}${bindir}/openssl-0.9
	mv ${D}${libdir}/libssl.so ${D}${libdir}/libssl-0.9.so
	mv ${D}${libdir}/libcrypto.so ${D}${libdir}/libcrypto-0.9.so
	mv ${D}${libdir}/libssl.a ${D}${libdir}/libssl-0.9.a
	mv ${D}${libdir}/libcrypto.a ${D}${libdir}/libcrypto-0.9.a
	mv ${D}${libdir}/pkgconfig/libcrypto.pc ${D}${libdir}/pkgconfig/libcrypto-0.9.pc
	mv ${D}${libdir}/pkgconfig/libssl.pc ${D}${libdir}/pkgconfig/libssl-0.9.pc
	mv ${D}${libdir}/pkgconfig/openssl.pc ${D}${libdir}/pkgconfig/openssl-0.9.pc
	rm -rf ${D}${libdir}/ssl ${D}${libdir}/engines
}

PACKAGES =+ "libcrypto-0.9 libssl-0.9"
FILES_libcrypto-0.9 = "${libdir}/libcrypto.so.*"
FILES_libssl-0.9 = "${libdir}/libssl.so.*"
