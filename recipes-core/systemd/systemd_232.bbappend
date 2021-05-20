do_install_append() {
      rm -rf ${D}${sysconfdir}/resolv.conf
      sed -i '/After=swap.target/d' ${D}${systemd_unitdir}/system/tmp.mount
}
