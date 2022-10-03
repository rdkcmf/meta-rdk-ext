### RDK SYSTEMD PROFILE ###

# Define the systemd features for RDK
PACKAGECONFIG = "timesyncd utmp myhostname sysusers"
PACKAGECONFIG += "${@bb.utils.contains('DISTRO_FEATURES', 'wifi', 'rfkill', '', d)}"


# Add unused files to systemd-extra-utils package
# NOTE: systemd-extra-utils package will not be installed to target
FILES_${PN}-extra-utils += " \
                        ${rootlibexecdir}/systemd/systemd-sysv-install \
                        ${rootlibexecdir}/systemd/system/sysinit.target.wants/systemd-machine-id-commit.service \
                        ${rootlibexecdir}/systemd/system/systemd-machine-id-commit.service \
                        ${base_bindir}/systemd-machine-id-setup \
                        ${bindir}/systemd-socket-activate \
"

# Remove files from systemd-extra-utils package so that they get installed to target
FILES_${PN}-extra-utils_remove = "${rootlibexecdir}/systemd/systemd-socket-proxyd ${bindir}/systemd-run ${bindir}/systemd-cat"

# Remove packages from systemd RRECOMMENDS list that are not used in RDK
# NOTE: systemd does not depend on this list of packages in order to successfully build, but rather uses them for extended usability
RRECOMMENDS_${PN}_remove = "${PN}-extra-utils ${PN}-vconsole-setup ${PN}-compat-units udev-hwdb"
