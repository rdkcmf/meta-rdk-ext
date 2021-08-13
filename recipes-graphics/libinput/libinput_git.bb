DESCRIPTION = "libinput"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=2184aef38ff137ed33ce9a63b9d1eb8f"

DEPENDS = "\
  udev \
  mtdev \
  libevdev \
"

RDEPENDS_${PN} += "\
  libatomic \
"

COMPATIBLE_MACHINE_mipsel = "(.*)"

FULL_OPTIMIZATION_remove = "-Os"
FULL_OPTIMIZATION_append = " -O3"

inherit cmake perlnative ${@bb.utils.contains('DISTRO_FEATURES', 'dunfell', 'python3native', 'pythonnative', d)}

SRC_URI = "\
  git://anongit.freedesktop.org/git/wayland/libinput;branch=master \
"

#SRCREV = "${AUTOREV}"
SRCREV = "41cc9053dd87e8b2be89187b73312d85a010c4ef"

LDFLAGS_append = " -Wl,--no-keep-memory"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "\
 --no-warn-unused-cli \
 -Wno-dev \
"

inherit autotools pkgconfig

do_configure() {
  NOCONFIGURE=true ${S}/autogen.sh
  oe_runconf
}

do_install_append() {
    cp -ar ${S}/src/*.h ${D}${prefix}/include
}

# Don't complain that .so file is not in -dev package
INSANE_SKIP_${PN} += "dev-so"

FILES_SOLIBSDEV = ""

FILES_${PN} += "${bindir}/*"
FILES_${PN} += "${libdir}/*.so ${libdir}/udev/*"
FILES_${PN}-dbg += "${libdir}/.debug/*.so ${libdir}/udev/.debug/*"
FILES_${PN}-dev += "${includedir}"

PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"
