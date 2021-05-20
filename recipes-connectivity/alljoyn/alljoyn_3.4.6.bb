# AllJoyn recipe
SUMMARY = "AllSeen Alliance Open Source Project"
SECTION = "libs/network"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://core/alljoyn/alljoyn_c/src/AuthListener.cc;beginline=7;endline=21;md5=65824a1f24921e5fe4c30a4365f38364"

AJ_GIT_BRANCH="RB15.04"

S = "${WORKDIR}/source"
SRC_URI = "git://github.com/alljoyn/core-alljoyn;branch=${AJ_GIT_BRANCH};destsuffix=${S}/core/alljoyn;name=core"
SRC_URI += "git://github.com/alljoyn/core-ajtcl;branch=${AJ_GIT_BRANCH};destsuffix=${S}/core/ajtcl;name=ajtcl"
SRC_URI += "git://github.com/alljoyn/services-base_tcl;branch=${AJ_GIT_BRANCH};destsuffix=${S}/services/base_tcl;name=basetcl"
SRC_URI += "git://github.com/alljoyn/services-base;branch=${AJ_GIT_BRANCH};destsuffix=${S}/services/base;name=base"

SRC_URI += "file://sconstruct_changes.patch \
            file://sconscript_changes.patch \
            file://0001-core-Fix-build-with-clang.patch \
            file://gcc5-scope.patch \
"
SRC_URI_append_morty = " file://fix_morty_errors.patch"
TARGET_CC_ARCH += "${LDFLAGS}"

SRCREV_FORMAT = "core_ajtcl_basetcl_base"
SRCREV_core = "6c492f7452a6b4b240f1b572dbda2f2bcc4faf2d"
SRCREV_ajtcl = "6ab10a087d14d25b9850cab025afb9e0c97a5a59"
SRCREV_basetcl = "91fcc0a2bdffd9630de71cc9c4d660c0d508841e"
SRCREV_base = "51d6850f77036fcfd624c54fd8d840748ab2aca5"

DEPENDS = "openssl libcap"
PROVIDES = "alljoyn"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_HOST = '(arm.*|x86_64.*|i.86.*)-(linux|freebsd.*)'

inherit autotools pkgconfig scons

AJ_SERVICES="about,config,notification,controlpanel"
REPO_LIST="core.alljoyn services.services_common services.config services.notification services.controlpanel services.sample_apps"

def get_alljoyn_cpu(a, d):
    import re
    if   re.match('(i.86|athlon|x86.64)$', a):
        return 'x86'
    elif re.match('(armeb|arm)$', a):
        return 'arm'
    else:
        bb.error("cannot map '%s' to a supported alljoyn target architecture" % a)

export ALLJOYN_CPU = "${@get_alljoyn_cpu(d.getVar('TARGET_ARCH', True), d)}"

do_compile() {
    cd ${S}/core/alljoyn
    export CROSS_COMPILE="${CC}"
    printenv
# Adding OE_BASE for the 14.06 build, this goes away in the 14.12 releases
    scons V=1 OS=linux CPU=${ALLJOYN_CPU} WS=off \
        CRYPTO=builtin \
        OE_BASE=/usr \
        BINDINGS=cpp \
        SERVICES=${AJ_SERVICES} \
        CC="${CC}" \
        CXX="${CXX}" \
        LINK="${CCLD}" \
        AR="${AR}" \
        RANLIB="${RANLIB}" \
        LINKFLAGS="${LDFLAGS}"
    echo "Linking bundled router"
    cd ${S}/core/alljoyn/build/linux/${ALLJOYN_CPU}/debug/dist/cpp/lib/
    ${CCLD} -fPIC -shared -o liballjoyn_router.so ../../../obj/alljoyn_core/router/bundled/BundledRouter.o libajrouter.a ${LINKFLAGS}
    cd ${S}/core/alljoyn
    echo "Compiled "
}

do_install() {
    AJ_BUILD_OUTPATH=${S}/core/alljoyn/build/linux/${ALLJOYN_CPU}/debug/dist/cpp
    echo "Include dir is ${includedir}"
    install -d ${D}/${libdir}
    install -d ${D}/${includedir}
    install ${AJ_BUILD_OUTPATH}/lib/*.so ${D}/${libdir}/
    cp -r -v ${AJ_BUILD_OUTPATH}/inc/* ${D}/${includedir}/
    install ${AJ_BUILD_OUTPATH}/lib/libajrouter.a ${D}/${libdir}/
    echo "Printing the list of header files in dest ${D}"
    find ${D}/${includedir}/alljoyn -iname "*.h"
    find ${D}/${libdir} -iname "*.so"
    find ${D}/${libdir} -iname "*.a"
    echo "Installed alljoyn"
}

FILES_SOLIBSDEV = ""
FILES_${PN} = "${libdir}/*.so"


