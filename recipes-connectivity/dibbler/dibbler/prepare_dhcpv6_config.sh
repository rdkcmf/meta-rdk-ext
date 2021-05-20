#!/bin/sh

if [ -f /etc/device.properties ];then
     . /etc/device.properties
fi

VENDOR_SPEC_FILE="/etc/udhcpc.vendor_specific"
OPTION_FILE="/tmp/vendor_spec.txt"
DHCP_CONFIG_FILE="/etc/dibbler/client.conf"
DHCP_CONFIG_FILE_RFS="/etc/dibbler/client_back.conf"
DHCP_CONFIG_FILE_RUNTIME="/tmp/dibbler/client.conf"
DHCP_CONFIG_FILE_TMP="/tmp/dibbler/client-tmp.conf"
DHCP_CONFIG_FILE_DIBBLER_TMP="/tmp/dibbler/dibbler-client-tmp.conf"
RDK_PATH="/lib/rdk"
interface=$ARM_INTERFACE

ethWanMode=`syscfg get eth_wan_enabled`
DSLite_Enabled=`syscfg get dslite_enable`
if [ "$interface" ] && [ -f /etc/dibbler/client_back.conf ];then
    sed -i "s/RDK-ESTB-IF/${interface}/g" /etc/dibbler/client_back.conf
fi
if [ ! -f /etc/dibbler/radvd.conf ];then touch /etc/dibbler/radvd.conf; fi
if [ ! -f /etc/dibbler/radvd.conf.old ];then touch /etc/dibbler/radvd.conf.old; fi
if [ -f /etc/os-release ];then
     if [ ! -f /tmp/dibbler/radvd.conf ];then touch /tmp/dibbler/radvd.conf; fi
     if [ ! -f /tmp/dibbler/radvd.conf.old ];then touch /tmp/dibbler/radvd.conf.old; fi
fi

if [ -f $OPTION_FILE ]; then
        rm -rf $OPTION_FILE
fi

updateOptInfo()
{
  opt_val=$1
  subopt_num=$2
  subopt_len=`echo ${#opt_val}`
  subopt_len_h=`printf "%04x\n" $subopt_len`;
  subopt_val_h=`echo -n $opt_val | hexdump -e '13/1 "%02x"'`
  echo -n $subopt_num$subopt_len_h$subopt_val_h >> $OPTION_FILE
  return
}

if [ "$DSLITE_DHCP_OPTION_ENABLED" = "true" ] && [ "$DSLite_Enabled" = "1" ];then
	echo  "        option aftr" >> $OPTION_FILE
fi

echo "        option 0016 hex 0x0000118b000a65526f75746572312e30" >> $OPTION_FILE

if [ "$EROUTER_DHCP_OPTION_EMTA_ENABLED" = "true" ] &&  [ "$ethWanMode" = "true" ];then 
	echo -n "        option 0017 hex 0x0000118b000100060027087A087B" >> $OPTION_FILE
else
	echo -n "        option 0017 hex 0x0000118b" >> $OPTION_FILE
fi
    while read line
    do
	mode=`echo $line | cut -f1 -d" "`
        opt_num=`echo $line | cut -f2 -d" "`
        opt_val=`echo $line | cut -f3 -d" "`
        case "$opt_num" in
            "SUBOPTION2")
                subopt_num="0002"
                updateOptInfo $opt_val $subopt_num
                ;;
            "SUBOPTION3")
                subopt_num="0003"
		if [ "$EROUTER_DHCP_OPTION_EMTA_ENABLED" = "true" ]  ;then 
			if [ "$mode" = "DOCSIS" ] && [ "$ethWanMode" = "true" ] ;then
				continue;
			fi

			if [ "$mode" = "ETHWAN" ] && [ "$ethWanMode" = "false" ] ;then
				continue;
			fi
		elif [ "$mode" = "ETHWAN" ] ;then
			continue;
		fi
                updateOptInfo $opt_val $subopt_num
                ;;
        esac;
    done < "$VENDOR_SPEC_FILE"

if [ "$EROUTER_DHCP_OPTION_EMTA_ENABLED" = "true" ] && [ "$ethWanMode" = "true" ];then 
    echo -n "0027000107" >> $OPTION_FILE
fi


if [ -f "$DHCP_CONFIG_FILE_RUNTIME" ]; then
      rm -rf $DHCP_CONFIG_FILE_RUNTIME
fi

if [ -f "$DHCP_CONFIG_FILE_TMP" ]; then
    rm -rf $DHCP_CONFIG_FILE_TMP
fi

sed '$d' $DHCP_CONFIG_FILE_RFS > $DHCP_CONFIG_FILE_TMP
cat $OPTION_FILE >> $DHCP_CONFIG_FILE_TMP
echo >> $DHCP_CONFIG_FILE_TMP
echo "}" >> $DHCP_CONFIG_FILE_TMP
sed -i "1i script \"/lib/rdk/client-notify.sh\"" $DHCP_CONFIG_FILE_TMP
# The following line to skip confirm message sending
echo "skip-confirm" >> $DHCP_CONFIG_FILE_TMP
TMP=`syscfg get IPv6subPrefix`
if [ "$TMP" = "true" ]         
then
IPv6subPrefix=56
sed -i "8i { prefix ::/56 }" $DHCP_CONFIG_FILE_TMP               
else
IPv6subPrefix=64
sed -i "8i { prefix ::/64 }" $DHCP_CONFIG_FILE_TMP
fi
#TCXB6-3863 add downlink-prefix-ifaces in client.conf
echo downlink-prefix-ifaces \"brlan0\" >> $DHCP_CONFIG_FILE_TMP

if [ "$BOX_TYPE" = "XB3" ] || [ "$MODEL_NUM" = "TG3482G" ]; then
	dibbler_client_enable=`syscfg get dibbler_client_enable`
        if [ "$dibbler_client_enable" = "true" ] ; then
		EMAC=`ifconfig erouter0 | grep HWaddr | cut -d"r" -f5 | cut -d" " -f2 |  cut -d":" -f3-6`
		if [ "$MODEL_NUM" = "TG3482G" ]
		then
			MAC=`echo $EMAC | awk '{split($1,a,":");printf("%s%s%s%s",a[4],a[3],a[2],a[1])}'`
		else
			MAC=`echo $EMAC | sed -e 's/://g'`
		fi
		IA=`printf "%d" 0x$MAC`
		sed -i "6d;7d;8d" $DHCP_CONFIG_FILE_TMP
		awk '/'$ARM_INTERFACE'/{print $0 RS "   t1 0" RS "      t2 0" RS "      prefered-lifetime 0" RS "       valid-lifetime 0" RS "  ia '$IA'" RS "{ address :: {" RS "      prefered-lifetime 0" RS "       valid-lifetime 0" RS "  }" RS "}" RS "  pd '$IA'" RS " { prefix ::/'$IPv6subPrefix' {" RS "     prefered-lifetime 0" RS "       valid-lifetime 0" RS "  }" RS "}" ;next}1' $DHCP_CONFIG_FILE_TMP > $DHCP_CONFIG_FILE_DIBBLER_TMP
		rm -rf $DHCP_CONFIG_FILE_TMP
		mv $DHCP_CONFIG_FILE_DIBBLER_TMP $DHCP_CONFIG_FILE_TMP
	fi
fi

ln -s $DHCP_CONFIG_FILE_TMP $DHCP_CONFIG_FILE_RUNTIME

