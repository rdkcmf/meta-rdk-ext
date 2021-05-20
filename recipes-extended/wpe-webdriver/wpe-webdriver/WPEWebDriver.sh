#!/bin/bash
#
##########################################################################
# If not stated otherwise in this file or this component's LICENSE
# file the following copyright and licenses apply:
#
# Copyright 2020 RDK Management
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
##########################################################################
#

if [ -f /lib/rdk/getRFC.sh ]; then
    . /lib/rdk/getRFC.sh WEBAUTOMATION
fi

if [ -f /opt/SetEnv.sh ] && [ "$BUILD_TYPE" != "prod" ]; then
    . /opt/SetEnv.sh
fi

if [ "xtrue" = "x$RFC_ENABLE_WEBAUTOMATION" -o "x1" = "x$RFC_ENABLE_WEBAUTOMATION" ]; then
    echo "Uing WEBAUTOMATION remote feature config: RFC_ENABLE_WEBAUTOMATION=$RFC_ENABLE_WEBAUTOMATION"
    if [ "xtrue" = "x$LAUNCH_STANDALONE_RDK_BROWSER" -o "x1" = "x$LAUNCH_STANDALONE_RDK_BROWSER" ]; then
         echo "Uing STANDALONE_RDK_BROWSER feature config: LAUNCH_STANDALONE_RDK_BROWSER=$LAUNCH_STANDALONE_RDK_BROWSER"
    else
         echo "Uing STANDALONE_RDK_BROWSER feature config: LAUNCH_STANDALONE_RDK_BROWSER=$LAUNCH_STANDALONE_RDK_BROWSER"
         unset LAUNCH_STANDALONE_RDK_BROWSER
    fi

    kill WPEWebDriver
    /usr/bin/WPEWebDriver --port=9517 --host=all
else
    echo "Uing WEBAUTOMATION remote feature config: RFC_ENABLE_WEBAUTOMATION=$RFC_ENABLE_WEBAUTOMATION"
fi
