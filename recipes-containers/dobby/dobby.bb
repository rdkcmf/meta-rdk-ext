SUMMARY = "Dobby Container Manager"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c466d4ab8a68655eb1edf0bf8c1a8fb8"

SRC_URI = "gitsm://github.com/rdkcentral/Dobby"

#2020-12-14
SRCREV = "7641cc2f126db2bc49b555ed59911848e75c0a21"
DEPENDS_append = " systemd libnl dbus jsoncpp ctemplate boost yajl python3"
RDEPENDS_${PN} = "crun (>= 0.14.1)"

S = "${WORKDIR}/git"

# Always build the debug version for now
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Debug "

# Sky XiOne build
EXTRA_OECMAKE_append_skyxione = "-DLEGACY_COMPONENTS=ON -DRDK_PLATFORM=XI1 "

# Sky Llama build
EXTRA_OECMAKE_append_llama = "-DLEGACY_COMPONENTS=ON -DRDK_PLATFORM=LLAMA "

# Comcast Xi6 build
EXTRA_OECMAKE_append_arrisxi6 = "-DLEGACY_COMPONENTS=OFF -DRDK_PLATFORM=XI6 "


inherit pkgconfig cmake systemd


# We use the cmake standard install, however it doesn't seem to correctly
# add the symlink for the systemd service, so here we do it as a post
# install step

do_install_append () {

    echo "Enabling service in ${D}${sysconfdir}/systemd/system/multi-user.target.wants/dobby.service"
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -sf ${systemd_system_unitdir}/dobby.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/dobby.service
}

# Skip harmless QA issue caused by installing but not shipping buildtime cmake files
INSANE_SKIP_${PN} = "installed-vs-shipped"

# These are the files that end up in the rootfs
FILES_${PN} += "${systemd_system_unitdir}/dobby.service"
FILES_${PN} += "${sysconfdir}/systemd/system/multi-user.target.wants/dobby.service"
FILES_${PN} += "${sysconfdir}/dobby.json"
FILES_${PN} += "${sbindir}/DobbyDaemon"
FILES_${PN} += "${libexecdir}/DobbyInit"
FILES_${PN} += "${bindir}/DobbyTool"
FILES_${PN} += "${libdir}/plugins/dobby/*.so*"