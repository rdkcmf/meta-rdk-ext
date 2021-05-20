#!/bin/sh

source /etc/utopia/service.d/log_env_var.sh
#LOGFILE=/var/lib/dibbler/server.sh-log

# uncomment this to get full list of available variables
#set >> $LOGFILE

echo "------------- $1 -------------" >> $LOGFILE

if [ "x$PREFIX1" != "x" ]; then
    echo "....$1 route for Prefix= ${PREFIX1} Prefix length= $PREFIX1LEN Remote Addr= $REMOTE_ADDR IFName= $IFACE ..." >> $LOGFILE
    if [ "$1" == "add" ]; then
       ip -6 route add ${PREFIX1}/$PREFIX1LEN via $REMOTE_ADDR dev $IFACE
    fi
    if [ "$1" == "update" ]; then
       ip -6 route add ${PREFIX1}/$PREFIX1LEN via $REMOTE_ADDR dev $IFACE
    fi
    if [ "$1" == "delete" ]; then
       ip -6 route del ${PREFIX1}/$PREFIX1LEN via $REMOTE_ADDR dev $IFACE
    fi
else
    echo "Prefix Address is NULL ..." >> $LOGFILE
fi
