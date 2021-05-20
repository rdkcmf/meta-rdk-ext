include gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                   "
SRC_URI[md5sum] = "f6b46f8fac01eb773d556e3efc369e86"
SRC_URI[sha256sum] = "f6d245b6b3d4cb733f81ebb021074c525ece83db0c10e932794b339b8d935eb7"

S = "${WORKDIR}/gst-plugins-base-${PV}"

do_install_append() {
       rm ${D}${datadir}/gst-plugins-base/1.0/license-translations.dict
}

FILES_${PN}_remove +="${datadir}/gst-plugins-base/1.0/license-translations.dict"

EXTRA_OECONF += "--enable-introspection=no"
