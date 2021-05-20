# If not stated otherwise in this file or this component's LICENSE file the
# following copyright and licenses apply:
#
# Copyright 2019 RDK Management
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
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



