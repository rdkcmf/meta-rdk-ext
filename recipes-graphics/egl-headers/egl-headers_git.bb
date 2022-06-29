DESCRIPTION = "OPEN GL and extension headers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/KhronosGroup/OpenGL-Registry.git;destsuffix=${S}/glesapi;branch=main;protocol=https;name=gles"
SRC_URI += "git://github.com/KhronosGroup/EGL-Registry.git;destsuffix=${S}/eglapi;branch=main;protocol=https;name=egl"
SRCREV_egl = "2d8e2b659f70cf3b28c7d6cedc408f853ba00a2e"
SRCREV_gles = "42f61786696df5102625d9b046976ee857645704"

# this is a Header package only, nothing to build
do_compile[noexec] = "1"
do_configure[noexec] = "1"

# also get rid of the default dependency added in bitbake.conf
# since there is no 'main' package generated (empty)
RDEPENDS_${PN}-dev = ""
# to include the headers in the SDK
ALLOW_EMPTY_${PN} = "1"

do_install() {
    install -d ${D}${includedir}/api
    install -d ${D}${includedir}/api/GLES
    install -m 0644 ${S}/glesapi/api/GLES/*.h ${D}${includedir}/api/GLES/
    install -d ${D}${includedir}/api/GLES2
    install -m 0644 ${S}/glesapi/api/GLES2/*.h ${D}${includedir}/api/GLES2/
    install -d ${D}${includedir}/api/GLES3
    install -m 0644 ${S}/glesapi/api/GLES3/*.h ${D}${includedir}/api/GLES3/

    install -d ${D}${includedir}/api/EGL
    install -m 0644 ${S}/eglapi/api/EGL/*.h ${D}${includedir}/api/EGL/
    install -d ${D}${includedir}/api/KHR
    install -m 0644 ${S}/eglapi/api/KHR/*.h ${D}${includedir}/api/KHR/
}
