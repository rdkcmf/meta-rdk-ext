#!/bin/sh

if [ -f /etc/device.properties ];then
. /etc/device.properties
fi

if [ $MODEL_NUM == "DPC3941B" ] || [ $MODEL_NUM == "DPC3939B" ] || [ "$MODEL_NUM" = "CGA4131COM" ] || [ "$MODEL_NUM" = "CGA4332COM" ]; then
    mkdir -p /var/dibbler
    mkdir -p /tmp/dibbler
    mkdir -p /var/lib/dibbler
    ln -s /tmp/dibbler /var/lib/dibbler
    touch /var/lib/dibbler/server.sh-log
    cp /lib/rdk/server-notify.sh /var/lib/dibbler/
fi 

exit 0

