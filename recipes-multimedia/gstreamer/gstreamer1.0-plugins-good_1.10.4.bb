include gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"
SRC_URI[md5sum] = "cc0cc13cdb07d4237600b6886b81f31d"
SRC_URI[sha256sum] = "8a86c61434a8c44665365bd0b3557a040937d1f44bf69caee4e9ea816ce74d7e"
S = "${WORKDIR}/gst-plugins-good-${PV}"

do_install_append(){
       rm ${D}${datadir}/gstreamer-1.0/presets/*.prs
}

FILES_${PN}_remove += " ${datadir}/gstreamer-1.0/presets/*.prs"

SRC_URI_append = " \
  file://0001-backported-qtdemux-distinguish-TFDT-with-value-0-from-no-TFDT-a.patch \
  file://0040-Backport-Keep-sample-data-from-the-current-fragment.patch \
"
