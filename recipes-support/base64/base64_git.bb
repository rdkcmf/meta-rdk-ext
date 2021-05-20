SUMMARY = "Base64 Libraries"
HOMEPAGE = "https://github.comcast.com"
SECTION = "libs"


LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0b6f2de0ac46fdfbc8cb55f3379981bf"

SRC_URI = "${RDK_COMPONENTS_ROOT_GIT}/opensource/base64/generic;protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH}"


SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"


inherit cmake
