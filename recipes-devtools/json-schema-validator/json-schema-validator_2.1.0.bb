SUMMARY = "JSON schema validator for JSON for Modern C++"
HOMEPAGE = "https://github.com/pboettch/json-schema-validator"
LICENSE = "MIT "
LIC_FILES_CHKSUM = "file://LICENSE;md5=c441d022da1b1663c70181a32225d006"

DEPENDS += " nlohmann-json"

SRC_URI = "git://github.com/pboettch/json-schema-validator.git;branch=main;protocol=https"
PV = "2.1.0+git${SRCPV}"

SRCREV = "27fc1d094503623dfe39365ba82581507524545c"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS=ON  -Dnlohmann_json_DIR=-I{STAGING_INCDIR}"

BBCLASSEXTEND = "native nativesdk"

FILES_${PN} += "${libdir}/* \
                ${bindir}/* "
