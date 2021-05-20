#RDK MANAGEMENT, LLC CONFIDENTIAL AND PROPRIETARY
# ============================================================================
# This file (and its contents) are the intellectual property of RDK Management, LLC.
# It may not be used, copied, distributed or otherwise  disclosed in whole or in
# part without the express written permission of RDK Management, LLC.
# ============================================================================
# Copyright (c) 2016 RDK Management, LLC. All rights reserved.
# ============================================================================
#

SUMMARY = "This recipes is used to compile and install log4cplus component"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cedaa287ececcb94f9f2880d9c4ef085"

SRCREV = "5394525f18dd59db9816c98401ca042514a83ffd"

SRC_URI = "git://github.com/rdkcteam/log4cplus-1.2.0;protocol=https \
          "

S = "${WORKDIR}/git"

inherit cmake coverity

INSANE_SKIP_${PN}-dev += "dev-elf"

FILES_${PN} += "/usr/*"

ALLOW_EMPTY_${PN} = "1"
