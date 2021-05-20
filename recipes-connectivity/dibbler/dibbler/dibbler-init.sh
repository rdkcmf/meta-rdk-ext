#!/bin/sh
#######################################################################
#   Copyright [2015] [ARRIS Corporation]
#
#   Licensed under the Apache License, Version 2.0 (the \"License\");
#   you may not use this file except in compliance with the License.
#   You may obtain a copy of the License at
#
#http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing, software
#   distributed under the License is distributed on an \"AS IS\" BASIS,
#   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#   See the License for the specific language governing permissions and
#   limitations under the License.
#######################################################################

if [ -f /etc/device.properties ];then
. /etc/device.properties
fi


## Function: removeIfNotLink
removeIfNotLink()
{
   if [ ! -h $1 ] ; then
        echo "Removing $1"
        rm -rf $1
   fi
}

echo "TLV_IP_MODE: IPv6 Mode..!"
if [ ! -f /etc/os-release ];then
   removeIfNotLink /var/lib/dibbler
if [ ! -e /var/lib/dibbler ]; then
   echo "Linking dibbler with /tmp/dibbler"
   ln -s /tmp/dibbler /var/lib/dibbler
fi
else
   if [ ! -h /tmp/dibbler ];then
      ln -s /etc/dibbler /tmp/dibbler
   fi
   if [ -f /tmp/dibbler/radvd.conf ];then
       chmod 644 /tmp/dibbler/radvd.conf
   fi 
   mkdir -p /var/log/dibbler
fi

echo > /tmp/dibbler/server.conf
ln -s /tmp/dibbler/server.conf /etc/dibbler/server.conf

#Preparing Configuration for dibbler client
if [ -f /lib/rdk/prepare_dhcpv6_config.sh ]; then
   /lib/rdk/prepare_dhcpv6_config.sh
fi

mkdir -p /var/dibbler
mkdir -p /tmp/dibbler
mkdir -p /var/lib/dibbler
ln -s /tmp/dibbler /var/lib/dibbler
touch /var/lib/dibbler/client.sh-log
cp /lib/rdk/client-notify.sh /var/lib/dibbler/

if [ -f /var/dibbler/client-duid ]; then
   cp /var/dibbler/client-duid /tmp/dibbler/client-duid
fi

#Will start dibbler-client from ccsp-p-and-m
#/usr/sbin/dibbler-client start
#sleep 5

if [ -f /tmp/dibbler/client-duid ]; then
   cp /tmp/dibbler/client-duid /var/dibbler/client-duid
fi

exit 0

