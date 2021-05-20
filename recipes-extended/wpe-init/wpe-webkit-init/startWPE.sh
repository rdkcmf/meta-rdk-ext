#!/bin/sh

url1=http://127.0.0.1:50050/menu.html
url2=http://easyhtml5video.com
url3=http://www.youtube.com/tv

url=$1
if [ $url ]; then
reqUrl=$url
else
reqUrl=$url1
fi

#Set environment

export WAYLAND_DISPLAY=WPE
export XDG_RUNTIME_DIR=/run/user/0/
export LD_PRELOAD=/usr/lib/libwesteros_gl.so.0.0.0

# start WPE-Webkit Browser if Available in startup

waitfornode=8
while [ ! -f $XDG_RUNTIME_DIR/WPE -a $waitfornode -gt 0 ];
do
    sleep 1
    echo "waiting for wayland display node to be created to launch browser app $waitfornode > 0 "
    waitfornode=$((waitfornode-1))
done

if [[ -f /usr/bin/WPELauncher && -f  $XDG_RUNTIME_DIR/WPE.lock ]]; then
/usr/bin/WPELauncher $reqUrl
fi

echo "Failed to launch WPEBrowser"

