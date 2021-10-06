DESCRIPTION = "Heaptrack"
HOMEPAGE = "https://phabricator.kde.org/source/heaptrack/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSES/LGPL-2.1-or-later.txt;md5=147a320ed8b16b036829a0c71d424153"
PR = "r0"

DEPENDS = "zlib boost libunwind elfutils"

SRC_URI = "git://github.com/KDE/heaptrack \
           file://rdk_heaptrack.patch \
           "
S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += " -DHEAPTRACK_BUILD_GUI=OFF"
FILES_${PN} += "/usr/bin"
FILES_${PN} += "/usr/lib"
FILES_${PN}-dbg += "/usr/lib/heaptrack/libexec/.debug"

