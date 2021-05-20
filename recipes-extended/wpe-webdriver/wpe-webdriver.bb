SUMMARY = "This receipe will start WPE-webdriver in startup"

LICENSE = "APACHE 2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/../meta-rdk/licenses/Apache-2.0;md5=3b83ef96387f14655fc854ddc3c6bd57"

PROVIDES = "wpe-webdriver"
RPROVIDES_${PN} = "wpe-webdriver"
RDEPENDS_${PN} += "bash"
SRC_URI =" \
     file://WPEWebDriver.sh \
     file://WPEWebDriver.service \
     "
S = "${WORKDIR}"

do_compile[noexec] = "1"

do_install() {
    install -Dm755 ${S}/WPEWebDriver.sh ${D}${bindir}/WPEWebDriver.sh
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/WPEWebDriver.service ${D}${systemd_unitdir}/system/
}

SYSTEMD_SERVICE_${PN} += "WPEWebDriver.service"
FILES_${PN} += "${sysconfdir}/* ${systemd_unitdir}/system/WPEWebDriver.service"
inherit systemd
