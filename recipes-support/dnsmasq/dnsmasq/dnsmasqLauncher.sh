#!/bin/busybox sh

if [ -f /etc/utopia/service.d/log_env_var.sh ];then
     . /etc/utopia/service.d/log_env_var.sh

else
   CONSOLEFILE="/dev/stdout"
fi

# Include Device configuration
if [ -f /etc/device.properties ];then
     . /etc/device.properties
fi

echo_t()
{
	if [ "$DEVICE_TYPE" = "broadband" ];then
            echo "`date +"%y%m%d-%T.%6N"` $1" >> $CONSOLEFILE
	else
	    echo "$1"
	fi
}

#Check if dnsmasq is already running. If yes, exit the script without restart
#NOTE: Process is not killed here if found running since it must be cleaned up by systemd during service restart
for DNS_PID in $(pidof dnsmasq)
do
    echo_t "dnsmasq already running"
    #If the running instance is in uninterruptible sleep (D), print lsof & mount o/p for future debugging
    DNS_STATE=`cat /proc/$DNS_PID/status | grep State | awk '{print $2}'`
    if [ "$DNS_STATE" = "D" ];then
        echo_t "dnsmasq is in uninterruptible sleep (D state)"
        echo_t "List of files opened by dnsmasq:"
        lsof -p $DNS_PID
        echo_t "Mount entries:"
        mount
    fi
    echo_t "Exiting without restart"
    exit 1
done

# Include RFC configuration
if [ -f /etc/rfc.properties ];then
     . /etc/rfc.properties
fi

# intialize the variables
DNS_OPTION=" "
DNS_BIN="/usr/bin/dnsmasq"
DNS_BROADBAND_OPTION=" "
DNSSTRICT_ORDER_STATUS="Device.DeviceInfo.X_RDKCENTRAL-COM_RFC.Feature.DNSStrictOrder.Enable"

if [ "$DEVICE_TYPE" = "broadband" ];then
     #ARRISXB3-9659 : Observed dnsmasq process not running on the ATOM side
     if [ "$BOX_TYPE" = "XB3" ]; then
          DNS_OPTIONS=" -N -a 127.0.0.1 -z "
     else
          # Broadband Devices like XF3
          DNS_OPTIONS=" -u nobody -q --clear-on-reload --cache-size=0 --add-mac --add-cpe-id=abcdefgh -P 4096 -C /etc/dnsmasq_vendor.conf "
          DNSSTRICT_ORDER_ENABLE=`syscfg get DNSStrictOrder`
     fi
else
     # RDK Video Platforms
     DNS_OPTIONS=" -N -a 127.0.0.1 -z "
     # RFC configuration File
     if [ ! "${TR181_STORE_FILENAME}" ];then
           if [ -f /opt/secure/RFC/tr181store.ini ];then
               TR181_STORE_FILENAME=/opt/secure/RFC/tr181store.ini
           else
               TR181_STORE_FILENAME=/opt/RFC/tr181store.ini
           fi
     fi
     # Ensure the strict order RFC setup
     DNSSTRICT_ORDER_ENABLE=`/usr/bin/tr181Set -g $DNSSTRICT_ORDER_STATUS 2>&1 > /dev/null`
     if [ $? -ne 0 ];then
         # Direct check on the tr181 Databse
         if [ -f ${TR181_STORE_FILENAME} ]; then
              DNSSTRICT_ORDER_ENABLE=`cat ${TR181_STORE_FILENAME} | grep ${DNSSTRICT_ORDER_STATUS} | cut -d "=" -f2`
         fi
     fi
fi

# Check for RFC Enable for DNS STRICT ORDER
if [ "x$DNSSTRICT_ORDER_ENABLE" == "xtrue" ]; then
      DNS_ADDITIONAL_OPTION=" -o "
else
      echo_t "RFC DNSTRICT ORDER is not defined or Enabled $DNSSTRICT_ORDER_ENABLE"
fi

# Log the new options
if [ "$DNS_OPTION" ];then
      echo_t "Starting dnsmasq with option: $DNS_OPTION" 
fi

# Starting the DNSMASQ based on the available DNS OPTION
if [ "$DNS_ADDITIONAL_OPTION" ];then
      echo_t "Starting dnsmasq with additional dns strict order option: $DNS_ADDITIONAL_OPTION" 
fi

if [ -f "$DNS_BIN" ];then
      "$DNS_BIN" $DNS_OPTIONS $DNS_ADDITIONAL_OPTION
else
      echo_t "dnsmasq Binary Not Found in the System path /usr/bin"
      dnsmasq $DNS_OPTIONS $DNS_ADDITIONAL_OPTION
fi
