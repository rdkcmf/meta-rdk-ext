# Copyright (C) 2005, Koninklijke Philips Electronics NV.  All Rights Reserved
# Released under the MIT license (see packages/COPYING)

SUMMARY = "Cross-platform, open-source make system"
DESCRIPTION = "CMake is used to control the software compilation process \
using simple platform and compiler independent configuration files. CMake \
generates native makefiles and workspaces that can be used in the compiler \
environment of your choice."
HOMEPAGE = "http://www.cmake.org/"
BUGTRACKER = "http://public.kitware.com/Bug/my_view_page.php"
SECTION = "console/utils"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://Copyright.txt;md5=c721f56fce89ba2eadc2fdd8ba1f4d83 \
                    file://Source/cmake.h;beginline=1;endline=2;md5=a5f70e1fef8614734eae0d62b4f5891b \
		    "

CMAKE_MAJOR_VERSION = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"

SRC_URI = "https://cmake.org/files/v${CMAKE_MAJOR_VERSION}/cmake-${PV}.tar.gz \
           file://0002-cmake-Prevent-the-detection-of-Qt5.patch \
           file://0003-cmake-support-OpenEmbedded-Qt4-tool-binary-names.patch \
           file://0004-Fail-silently-if-system-Qt-installation-is-broken.patch \
	   file://0001-cm_cxx_features.cmake-do-not-try-to-run-the-test-bin.patch \
"

SRC_URI[sha256sum] = "e3e0fd3b23b7fb13e1a856581078e0776ffa2df4e9d3164039c36d3315e0c7f0"

UPSTREAM_CHECK_REGEX = "cmake-(?P<pver>\d+(\.\d+)+)\.tar"
