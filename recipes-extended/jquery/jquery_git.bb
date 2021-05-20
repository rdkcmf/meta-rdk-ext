SUMMARY = "JQuery"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://js/jquery-1.4.4.min.js;endline=15;md5=2c140a9bd07caf7410ede3295c3672fa"
PV = "${RDK_RELEASE}+git${SRCPV}"

SRC_URI = "${RDK_COMPONENTS_ROOT_GIT}/opensource/jquery/generic;protocol=${RDK_GIT_PROTOCOL};branch=${RDK_GIT_BRANCH}"

S = "${WORKDIR}/git"

SRCREV = "${AUTOREV}"

do_install() {
        install -m 0755 -d ${D}/var/www/htmldiag/js
        install -m 0755 -d ${D}/var/www/hwselftest/scripts
        install -m 0755 -d ${D}/var/www/htmldiag2/common/js
        install -m 0755 -d ${D}/var/www/shared
        
        # Creating symbol link to file jquery-1.4.4.min.js from shared folder.
        cp -a ${S}/js/* ${D}/var/www/shared
        cp ${D}/var/www/shared/jquery-1.4.4.min.js ${D}/var/www/hwselftest/scripts/jquery-1.4.4.min.js
        cp ${D}/var/www/shared/jquery-1.4.4.min.js ${D}/var/www/htmldiag/js/jquery-1.4.4.min.js
        cp ${D}/var/www/shared/jquery-1.4.4.min.js ${D}/var/www/htmldiag2/common/js/jquery-1.4.4.min.js
}

FILES_${PN} += " /var/www/shared \              
                 /var/www/htmldiag/js \
                 /var/www/htmldiag2/js \
                 /var/www/hwselftest/scripts \"

ALLOW_EMPTY_${PN} = "1"

