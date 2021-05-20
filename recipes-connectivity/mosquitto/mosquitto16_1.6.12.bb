SUMMARY = "Open source MQTT v3.1 implemention"
DESCRIPTION = "Mosquitto is an open source (BSD licensed) message broker that implements the MQ Telemetry Transport protocol version 3.1. MQTT provides a lightweight method of carrying out messaging using a publish/subscribe model. "
HOMEPAGE = "http://mosquitto.org/"
SECTION = "console/network"
LICENSE = "EPL-1.0 | EDL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=62ddc846179e908dc0c8efec4a42ef20"

DEPENDS = "openssl util-linux python c-ares"

PR = "r0"

# Don't autoname this library package, just use the PN.
# Otherwise, debian.bbclass will name packages after the DSOs
# which will clash with the normal libledger packages.
AUTO_LIBNAME_PKGS = ""

S = "${WORKDIR}/mosquitto-1.6.12"

SRC_URI = "http://mosquitto.org/files/source/mosquitto-${PV}.tar.gz \
           file://build-1.6.12.patch \
           file://mosquitto.service \
"

export LIB_SUFFIX="${@d.getVar('baselib', True).replace('lib', '')}"

SRC_URI[md5sum] = "beb8d76d6e45f1e66d711006082a631f"
SRC_URI[sha256sum] = "548d73d19fb787dd0530334e398fd256ef3a581181678488a741a995c4f007fb"

do_compile() {
    oe_runmake PREFIX=/usr
}

do_install() {
    #oe_runmake install DESTDIR=${D}
    install -d ${D}${libdir}
    install -m 0644 lib/libmosquitto.a ${D}${libdir}/libmosquitto16.a

   #Provide headers to the dev pacakge for other components.
   #Anyone that wants to build against this interface must
   #provide this path in the STAGING_INCDIR to their build.
   install -d ${D}${includedir}/${PN}/mosquitto
   install -m 0644 "${S}/lib/mosquitto.h" ${D}${includedir}/${PN}/
}
