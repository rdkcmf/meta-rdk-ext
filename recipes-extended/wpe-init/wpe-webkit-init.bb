SUMMARY = "This receipe will start WPE-webkit launcher in startup"

LICENSE = "APACHE 2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-rdk-video/licenses/Apache-2.0;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI =" \
     file://startWPE.sh \
     file://wpe-launcher.service \ 
     "
S = "${WORKDIR}"

do_compile[noexec] = "1"

do_install() {

install -Dm755 ${S}/startWPE.sh ${D}${bindir}/startWPE.sh
install -d ${D}${systemd_unitdir}/system
install -m 0644 ${S}/wpe-launcher.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} = "wpe-launcher.service"
FILES_${PN} += "${systemd_unitdir}/system/wpe-launcher.service"
inherit systemd
