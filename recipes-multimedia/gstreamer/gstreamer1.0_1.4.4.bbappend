FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0001-backported-protection-event-from-gst-1.5.x.patch \
                   file://0002-WKIT-379-gst-backported-stability-changes-from-upstream.patch"

do_install_append(){
if [ -f ${D}${libdir}/gstreamer1.0/gstreamer-1.0/gst-plugin-scanner ]; then
       rm ${D}${libdir}/gstreamer1.0/gstreamer-1.0/gst-plugin-scanner
fi
}

FILES_${PN}_remove += "${libdir}/usr/lib/gstreamer1.0/gstreamer-1.0/gst-plugin-scanner"

EXTRA_OECONF += "--enable-introspection=no"
