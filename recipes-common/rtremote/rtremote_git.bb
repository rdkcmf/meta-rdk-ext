DESCRIPTION = "rtRemote"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4a3b4a11957d110d45f91f98a742e0f3"

DEPENDS = " util-linux rt-headers "

PV = "2.x+git${SRCPV}"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/rdkcentral/rtRemote;branch=master"
SRCREV = "3db90b4881a2022f6222addbf6506419c4c2c281"

inherit cmake

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://rtremote.conf "

ARCHFLAGS_append_arm = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--with-arm-float-abi=hard', '--with-arm-float-abi=softfp', d)}"
ARCHFLAGS_append_mipsel = " --with-mips-arch-variant=r1"
ARCHFLAGS ?= ""
SELECTED_OPTIMIZATION_remove = "-O1"
SELECTED_OPTIMIZATION_remove = "-O2"
SELECTED_OPTIMIZATION_remove = "-Os"
SELECTED_OPTIMIZATION_append = " -O3"
SELECTED_OPTIMIZATION_append = " -Wno-deprecated-declarations -Wno-maybe-uninitialized -Wno-address"

TARGET_CFLAGS += " -fno-delete-null-pointer-checks "
TARGET_CXXFLAGS += " -fno-delete-null-pointer-checks "
TARGET_CXXFLAGS += " -Wl,--warn-unresolved-symbols "

EXTRA_OECMAKE += " -DRTREMOTE_GENERATOR_EXPORT=${S}/temp/rtRemoteConfigGen_export.cmake "
EXTRA_OECMAKE += " -DRT_INCLUDE_DIR=${STAGING_INCDIR}/pxcore "

do_configure_prepend() {
    if [ ! -d ${S}/temp ]; then
    mkdir ${S}/temp
    fi
    cd ${S}/temp
    cmake -DCMAKE_CROSSCOMPILING=OFF -URTREMOTE_GENERATOR_EXPORT -DCMAKE_C_FLAGS=${BUILD_CFLAGS} -DCMAKE_C_COMPILER=${BUILD_CC} -DCMAKE_CXX_COMPILER=${BUILD_CXX} -DCMAKE_CXX_FLAGS=${BUILD_CXX_FLAGS} ..
    cmake --build . --target rtRemoteConfigGen
    rm -rf ${S}/temp/CMakeCache.txt
    rm -rf ${S}/temp/Makefile
    rm -rf ${S}/temp/cmake_install.cmake
    rm -rf ${S}/temp/CMakeFiles 
    cd ..
}

do_install () {
   install -d ${D}/usr/lib
   cp -a ${S}/librtRemote* ${D}/usr/lib

   mkdir -p ${D}${includedir}/pxcore
   install -m 0644 ${S}/include/rtRemote.h ${D}${includedir}/pxcore/
   install -m 0644 ${S}/include/rtRemote.h ${D}${includedir}/
   cp -R ${S}/external/rapidjson/ ${D}${includedir}/pxcore/

   mkdir -p ${D}/etc
   install -m 0644 "${WORKDIR}/rtremote.conf" "${D}/etc/"
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so staticdev"
INSANE_SKIP_${PN}_append_morty = " ldflags"
DEBIAN_NOAUTONAME_${PN} = "1"

BBCLASSEXTEND = "native"

