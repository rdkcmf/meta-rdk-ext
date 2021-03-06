# The bake file builds gflags which is a C++ library thet implements parameter processing
# author : derric_lynns@comcast.com
#

DESCRIPTION = "The gflags package contains a C++ library that implements commandline flags processing. It includes built-in support for standard types such as string and the ability to define flags in the source file in which they are used"

# license information
HOMEPAGE = "https://github.com/gflags/gflags"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=c80d1a3b623f72bb85a4c75b556551df"

# source url
SRC_URI = "git://github.com/gflags/gflags.git"
SRCREV = "f8a0efe03aa69b3336d8e228b37d4ccb17324b88"

S = "${WORKDIR}/git"

inherit cmake

# build flags
EXTRA_OECMAKE="-DBUILD_SHARED_LIBS=ON -DREGISTER_INSTALL_PREFIX=OFF -DLIB_INSTALL_DIR=${baselib}"

PACKAGES =+ "${PN}-bash-completion"
FILES_${PN}-bash-completion += "${bindir}/gflags_completions.sh"

RDEPENDS_${PN}-bash-completion = "bash bash-completion"

BBCLASSEXTEND = "native nativesdk"



