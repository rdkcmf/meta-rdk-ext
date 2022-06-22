
PACKAGECONFIG_remove = "gnutls"
PACKAGECONFIG_append = " openssl"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://openssl_no_md4.patch"

do_configure_append () {
   # Add the "-fPIC" option to CFLAGS to allow the Pace WiFi HAL module to link against wpa-supplicant
   echo "CFLAGS += -fPIC" >> wpa_supplicant/.config

   echo "CONFIG_BUILD_WPA_CLIENT_SO=y" >> wpa_supplicant/.config

   if grep -q '\bCONFIG_DEBUG_FILE\b' wpa_supplicant/.config; then
      sed -i -e '/\bCONFIG_DEBUG_FILE\b/s/.*/CONFIG_DEBUG_FILE=y/' wpa_supplicant/.config
   else
      echo "CONFIG_DEBUG_FILE=y" >> wpa_supplicant/.config
   fi

   sed -i -- 's/CONFIG_AP=y/\#CONFIG_AP=y/' wpa_supplicant/.config
   sed -i -- 's/CONFIG_DRIVER_HOSTAP=y/\#CONFIG_DRIVER_HOSTAPAP=y/' wpa_supplicant/.config

   echo "OPENSSL_NO_MD4=y" >> wpa_supplicant/.config
}

do_install_append () {
   install -d ${D}${includedir}
   install -m 0644 ${S}/src/common/wpa_ctrl.h ${D}${includedir}

   install -d ${D}${libdir}
   install -m 0644 ${S}/wpa_supplicant/libwpa_client.so ${D}${libdir}
}

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/libwpa_client.so"
