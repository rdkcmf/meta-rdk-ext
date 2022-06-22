SUMMARY = "C++ library for JSON Schema validation"
HOMEPAGE = "https://github.com/pboettch/json-schema-validator"
LICENSE = "MIT "
LIC_FILES_CHKSUM = "file://LICENSE;md5=c441d022da1b1663c70181a32225d006"

DEPENDS += " nlohmann-json"

SRC_URI = "git://github.com/pboettch/json-schema-validator.git"
PV = "2.0+git${SRCPV}"

SRCREV = "7264fa0a05d6ef3e0d3d25beac02fdf4a39280f1"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS=ON  -Dnlohmann_json_DIR=-I{STAGING_INCDIR}"

BBCLASSEXTEND = "native nativesdk"

FILES_${PN} += "${libdir}/* \
                ${bindir}/* "
