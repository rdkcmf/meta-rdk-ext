SUMMARY = "Container generator tool"
DESCRIPTION = "The container generator and other components are used to post process root file system and create environment for containers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV="3"
PR="0"

SRC_URI = "${CMF_GIT_ROOT}/rdk/components/generic/lxc-container-generator;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_MASTER_BRANCH};name=lxc-container-generator"
SRCREV ?= "${AUTOREV}"
S = "${WORKDIR}/git"

inherit native

install_lxc_config() {
	if [ "$#" -eq 2 ]; then
		if [ -f ${WORKDIR}/xml/$2 ]; then
			if [ -d ${D}${datadir}/${BPN}/$1 ]; then
				install -m 644 ${WORKDIR}/xml/$2 ${D}${datadir}/${BPN}/$1/
			else
				echo "install_lxc_config: Directory ${D}${datadir}/${BPN}/$1/ does not exists!"
				exit 1
			fi
		fi
	else
		echo "install_lxc_config: Wrong number of parameters!"
		exit 1
	fi
}

do_install() {
	install -d ${D}${datadir}/${BPN}
	install -d ${D}${datadir}/${BPN}/secure
	install -d ${D}${datadir}/${BPN}/non_secure
	install -d ${D}${datadir}/${BPN}/src
	install -d ${D}${datadir}/${BPN}/src/lib
        install -m 755 ${S}/src/*.py ${D}${datadir}/${BPN}/src/
	install -m 755 ${S}/src/lib/*.py ${D}${datadir}/${BPN}/src/lib/
        install -d ${D}${datadir}/${BPN}/src/conf
        install -m 755 ${S}/src/conf/config.ini ${D}${datadir}/${BPN}/src/conf/config.ini

	#install_lxc_config non_secure lxc_conf_EXAMPLE.xml
	#install_lxc_config non_secure lxc_conf_EXAMPLE_appendsample.xml
}
