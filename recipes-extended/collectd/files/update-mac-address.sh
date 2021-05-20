#!/bin/bash

source /lib/rdk/utils.sh
macAddr=`getMacAddressOnly`
new_conf=`sed "s/^Hostname.*/Hostname \"$macAddr\"/" /etc/collectd.conf`
echo "$new_conf" > /etc/collectd.conf
