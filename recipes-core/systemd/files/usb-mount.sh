#!/bin/sh

# This script is called from our systemd unit file to mount or unmount
# a USB drive.
# Logging to verify USB status
if [ -f /etc/include.properties ];then
     . /etc/include.properties
fi

if [ -f /etc/device.properties ];then
     . /etc/device.properties
fi

IARM_EVENT_BINARY_LOCATION=/usr/bin
if [ ! -f /etc/os-release ]; then
    IARM_EVENT_BINARY_LOCATION=/usr/local/bin
fi

if [ "$LOG_PATH" ];then
     LOG_FILE="$LOG_PATH/rdm_status.log"
else
     if [ -d /var/log ];then
          if [ -f /var/log/rdm_status.log ];then
               rm -rf /var/log/rdm_status.log
          fi
          LOG_FILE=/var/log/rdm_status.log
     else
          LOG_FILE=/dev/null
     fi
fi

log_msg() {
  #get current dateandtime
  DateTime=`date "+%m%d%y-%H:%M:%S:%N"`
  STR=""
  #check if parameter non zero size
  if [ -n "$1" ];
  then
    STR="$1"
  else
    DONE=false
    until $DONE ;do
    read IN || DONE=true
    STR=$STR$IN
    done
  fi
  #print log message
  echo "[$DateTime] [pid=$$] $STR" >>$LOG_FILE
}

usage()
{
    log_msg "Usage: $0 {add|remove} device_name (e.g. sdb1)"
    exit 1
}

if [[ $# -ne 2 ]]; then
    usage
fi

ACTION=$1
DEVBASE=$2
DEVICE="/dev/${DEVBASE}"
EVENT_MOUNT_POINT="/run/media/${DEVBASE}"

if [[ ${DEVBASE} == "" ]]; then
    log_msg "No Device specified"
    exit
fi

if [ -f $IARM_EVENT_BINARY_LOCATION/IARM_event_sender ]; then
    if [ "$ACTION" = "add" ]; then
        MOUNT=1
    else
        MOUNT=0
    fi
    $IARM_EVENT_BINARY_LOCATION/IARM_event_sender "USBMountChangedEvent" $MOUNT $DEVICE $EVENT_MOUNT_POINT
else
    log_msg "Missing the binary $IARM_EVENT_BINARY_LOCATION/IARM_event_sender"
fi

if [ "$BOX_TYPE" != "pi" ];then

        log_msg "Checking RFC control for USB automount feature"
        # RFC verification if USB auto mount is enabled or disabled
        USBMOUNT_RFC_ENABLE=`/usr/bin/tr181Set -g Device.DeviceInfo.X_RDKCENTRAL-COM_RFC.Feature.USB_AutoMount.Enable 2>&1 > /dev/null`

        if [ "x$USBMOUNT_RFC_ENABLE" != "xtrue" ]; then
            log_msg "Exiting from USB mounting: RFC Support disabled for USBMOUNT_RFC_ENABLE"
            exit 0
        fi
else
        log_msg "USB Automount feature is enabled in Raspberry pi devices."
fi

# See if this drive is already mounted, and if so where
CUR_MOUNT_POINT=$(/bin/mount | /bin/grep ${DEVICE} | /usr/bin/awk '{ print $3 }')

if [ "$RDK_APP_USB_MOUNT_POINT" ];then
      log_msg "Device is configured with mount point: $RDK_APP_USB_MOUNT_POINT"
else
     RDK_APP_USB_MOUNT_POINT=/usb
     if [ ! -d /usb ];then
           log_msg "Not Found the configured mount point in the system"
           log_msg "Mount point is /tmp/ with Device Label"
           RDK_APP_USB_MOUNT_POINT=/tmp/usb
           if [ ! -d /tmp/usb ];then
                mkdir -p /tmp/usb
           fi
     fi
fi

do_mount()
{
    if [ -n ${CUR_MOUNT_POINT} ] && [ "$RDK_APP_USB_MOUNT_POINT" = "$CUR_MOUNT_POINT" ]; then
        log_msg "Warning: ${DEVICE} is already mounted at ${CUR_MOUNT_POINT}"
        exit 1
    fi

    # Get info for this drive: $ID_FS_LABEL, $ID_FS_UUID, and $ID_FS_TYPE
    #eval $(/sbin/blkid -o udev ${DEVICE})

    # Figure out a mount point to use
    LABEL=$(/sbin/blkid -o udev ${DEVICE} | sed -n 's/.*LABEL=\"\([^\"]*\)\".*/\1/p')
    if [[ ${LABEL} == "" ]]; then
        LABEL=${DEVBASE}
    fi
    if /bin/grep -q " /tmp/${LABEL} " /etc/mtab; then
        # Already in use, make a unique one
        LABEL+="-${DEVBASE}"
    fi
    MOUNT_POINT="$RDK_APP_USB_MOUNT_POINT"
    if [ $MOUNT_POINT = "/tmp" ];then
         MOUNT_POINT=/tmp/${LABEL}
         /bin/mkdir -p $MOUNT_POINT
    fi
    log_msg "Mount point: $MOUNT_POINT"

    # Global mount options
    OPTS="rw,relatime"

    # File system type specific mount options
    TYPE=$(/sbin/blkid -o udev ${DEVICE} | sed -n 's/.*TYPE=\"\([^\"]*\)\".*/\1/p')
    # ext3 file system type option
    if [[ ${TYPE} == "ext3" ]]; then
        OPTS+=",data=ordered"
        if ! /bin/mount -o ${OPTS} ${DEVICE} $MOUNT_POINT; then
            log_msg "Error mounting ${DEVICE} (status = $?)"
            /bin/rm -rf ${MOUNT_POINT}
            exit 1
        fi
    else
        if ! /bin/mount  ${DEVICE} $MOUNT_POINT; then      
            log_msg "Error mounting ${DEVICE} (status = $?)"
            exit 1                  
        fi
    fi


    log_msg "Successfully Mounted the Device"
    log_msg "**** Mounted ${DEVICE} at ${MOUNT_POINT} ****"
    # Calling RDM Script for signature validation & update App
    # manager configuration against packages present
    if [ -f /etc/rdm/downloadUSBPackage.sh ];then
          sh /etc/rdm/downloadUSBPackage.sh ${MOUNT_POINT}
    else
          log_msg "Execution Script Not Found [/etc/rdm/downloadUSBPackage.sh ]"
    fi
    if [ $? -ne 0 ]; then
         log_msg "Validation/extraction for package resides ${MOUNT_POINT} Failed"
    fi 
}

do_unmount()
{
    if [[ -z ${CUR_MOUNT_POINT} ]]; then
        log_msg "Warning: ${DEVICE} is not mounted"
    else
        /bin/umount -l ${DEVICE}
        #/bin/rm -rf ${CUR_MOUNT_POINT}
        log_msg "**** Unmounted ${DEVICE}"
    fi

}

case "${ACTION}" in
    add)
        do_mount
        ;;
    remove)
        do_unmount
        ;;
    *)
        usage
        ;;
esac
