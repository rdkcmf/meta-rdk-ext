FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
        file://socat_restrict_localhost.patch \
        file://socat_windows_config_pty.patch \
        "
EXTRA_OECONF += " --disable-proxy \
		--disable-tun \
		--disable-stdio \
		--disable-sctp \
		--disable-socks4 \
		--disable-socks4a \
		--disable-udp \
		--disable-fdnum \
		--disable-creat \
		--disable-gopen \
		--disable-pipe \
		--disable-unix \
		--disable-abstract-unixsocket \
		--disable-rawip \
		--disable-genericsocket \
		--disable-system \
		--disable-readline \
		--disable-filan \
"
