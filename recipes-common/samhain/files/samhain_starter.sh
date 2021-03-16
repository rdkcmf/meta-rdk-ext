#!/bin/sh
##########################################################################
# If not stated otherwise in this file or this component's Licenses.txt
# file the following copyright and licenses apply:
#
# Copyright 2021 RDK Management
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

. /etc/include.properties
. /etc/device.properties

if [ "$BUILD_TYPE" != "prod" ] ; then
    # Disable samhain if its a USB booted developer device
    if [ -w /version.txt ]; then
        usbBootStatus='rw'
    else
        usbBootStatus='ro'
    fi

    if [ "$usbBootStatus" == "rw" ]; then
        # Root is mounted to /dev location !! Ignore Samhain start
        echo "INFO : This is a $BUILD_TYPE usb booted device. Samhain monitoring is disabled !!!"
        exit 0
    fi
fi

# Check for previous running insatnce to avoid multiple execution
samhainPid=`pidof samhain`
if [ ! -z "$samhainPid" ]; then
    echo "Previous running instance of samhain with PIDs $samhainPid is present. Ignore samhain start."
    exit 0
fi

if [ -f  /lib/rdk/getRFC.sh ]; then
    . /lib/rdk/getRFC.sh IDS
fi

if [ "x$RFC_ENABLE_IDS" == "xtrue" ]; then
    echo "INFO : Samhain monitoring enabled with RFC value for RFC_ENABLE_IDS = $RFC_ENABLE_IDS"
    echo "INFO : Samhain monitoring is in enabled mode. Starting samhain !!!"
else
    echo "INFO : Samhain monitoring disabled with RFC value for RFC_ENABLE_IDS = $RFC_ENABLE_IDS"
    echo "INFO : IDS is disabled by default. Samhain monitoring is disabled !!!"
    exit 0
fi

if [ -d /tmp/samhain ]; then
    rm -rf /tmp/samhain
fi
mkdir -p /tmp/samhain

if [ ! -f /etc/samhain_file ]; then

  if [ -f /opt/md5_version.txt ]; then
   currentRunningImageMd5=`/usr/bin/md5sum /version.txt | cut -d ' ' -f1`
   previousFlashedImageMd5=`cut -d ' ' -f1 /opt/md5_version.txt`

   if [ "x$currentRunningImageMd5" != "x$previousFlashedImageMd5" ]; then
       # This is new image upgrade. Clear the baseline file
       rm -f /opt/samhain_file
   fi
  else 
   rm -f /opt/samhain_file
  fi

  isInit="false"
  if [ ! -f /opt/samhain_file ];then
    rm -f /tmp/samhain.pid
    # Wait for the device to stabilize before generating the database
    sleep 180
    nice -n 30 /usr/sbin/samhain -t init
    /usr/bin/md5sum /version.txt > /opt/md5_version.txt
    isInit="true"
  fi

  if [ "x$isInit" == "xtrue" ]; then
    sleep 5
  else 
    sleep 180
  fi
fi

## Selective enabling of samhain features
TEMP_IDS_CONFIG="/tmp/samhainrc"
if [ -f $TEMP_IDS_CONFIG ]; then
    rm -f $TEMP_IDS_CONFIG
fi
cp /etc/samhainrc $TEMP_IDS_CONFIG
# Update config if selective settings are available 
if [ ! -x "$RFC_DATA_IDS_SCAN_TASK" ]; then
    echo "IDS selectively enabled policies are $RFC_DATA_IDS_SCAN_TASK"
    echo "File monitoring policy will be turned ON by default"
    if [[ $RFC_DATA_IDS_SCAN_TASK != *".MOUNT."* ]]; then
        sed -i -e "s|MountCheckActive.*|MountCheckActive = no|g" $TEMP_IDS_CONFIG
    fi

    if [[ $RFC_DATA_IDS_SCAN_TASK != *".PORT."* ]]; then
        sed -i -e "s|PortCheckActive.*|PortCheckActive = no|g" $TEMP_IDS_CONFIG
    fi

    if [[ $RFC_DATA_IDS_SCAN_TASK != *".PROC."* ]]; then
        sed -i -e "s|ProcessCheckActive.*|ProcessCheckActive = no|g" $TEMP_IDS_CONFIG
    fi

    if [[ $RFC_DATA_IDS_SCAN_TASK != *".LOG."* ]]; then
        sed -i -e "s|LogmonActive.*|LogmonActive = no|g" $TEMP_IDS_CONFIG
    fi

else
    echo "IDS selective enable policies are not present. All policies will be enabled by default" 
fi

mount-copybind $TEMP_IDS_CONFIG /etc/samhainrc

mkdir -p /opt/logs
touch /opt/logs/dropbear.log
touch /opt/logs/messages.txt

if [ "$DEVICE_TYPE" = "mediaclient" ]; then
    touch /opt/logs/webpavideo.log
    touch /opt/logs/tr69agent_SoapDebug.log
fi

if [ "$DEVICE_TYPE" = "hybrid" ]; then
    touch /opt/logs/snmpd.log
fi

rm -f /tmp/samhain.pid
touch /tmp/samhain-enabled

exit 0

