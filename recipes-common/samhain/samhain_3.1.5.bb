SECTION = "console/utils"

SUMMARY = "Intrusion detection system"

DESCRIPTION = "samhain is a file system integrity checker for both single hosts\
and networks. You can trace what changes have occurred in your system, when they\ 
occurred, and who was logged in at the time. The program uses cryptographic\
checksums to monitor file integrity and detect unauthorized modifications of\ 
a file system. It's designed for tamper resistance, even if an intruder has\ 
obtained root privileges."

HOMEPAGE = "http://www.la-samhna.de/samhain"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ca43cbc842c2336e835926c2166c28b"

SRC_URI = "http://la-samhna.de/archive/samhain_signed-${PV}.tar.gz"

inherit systemd
                                                                                                    
FILES_${PN} += "${systemd_unitdir}/system/samhain.service"
FILES_${PN} += "${systemd_unitdir}/system/samhain-precheck.service"
FILES_${PN} += "${systemd_unitdir}/system/samhain.path"
FILES_${PN} += "${base_libdir}/rdk/samhain_starter.sh"
SYSTEMD_SERVICE_${PN} = "samhain.service"
SYSTEMD_SERVICE_${PN} += "samhain-precheck.service"
SYSTEMD_SERVICE_${PN} += "samhain.path"

SRC_URI += "file://fix_for_false_positives_with_build_time_baseline.patch \
        file://samhain_src.patch \
	file://configure \
	file://Makefile.in \
	file://sample.c \
	file://samhain.service \
	file://samhain-precheck.service \
	file://samhain.path \
        file://samhainrc \
        file://samhain_starter.sh \
	"

SRC_URI_append = " file://inotify_handler_for_new_mounts.patch"
SRC_URI_append = " file://fix_false_errors_for_kernel_threads.patch"
SRC_URI_append = " file://correct_format_logmon.patch"
SRC_URI_append = " file://memory_leak_fix.patch"
SRC_URI_append = " file://DELIA-24910_null_process.patch"

SRC_URI[md5sum] = "b72b526c6ee504317981e6dba1673b9d"
SRC_URI[sha256sum] = "2d7d93992d1737b72ff7c835b4c90a2d4d46e448d9265c0c3341544f6b1ffcdb"

LDFLAGS += "-g -pthread -L."
DEPENDS += "acl attr zlib e2fsprogs"

HOST_GCC = "${@oe.utils.host_gcc_version(d)}"
PATH_prepend := "${@bb.utils.contains('HOST_GCC', '6.4', '/usr/bin:', '', d)}"

INSANE_SKIP_${PN} = "ldflags"

# We have to unpack the tar ball twice to get to the source.
# Also as soon as OE gets the tar ball it unpacks and
# proceeds to apply the patches. But what you still have after
# the first unpack is another tar ball. So we do a do_unpack_extra()
# and tell OE to do the second unpack before do_patch(), otherwise
# do_patch() will fail when trying to apply the patches.
base_do_unpack_extra () {
        cd ${WORKDIR}
        tar -xzvf samhain-${PV}.tar.gz
}

python do_unpack () {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('base_do_unpack_extra', d)
}

do_configure() {
	cd ${WORKDIR}
	cp -f samhain.service samhain-precheck.service samhain.path samhain_starter.sh configure Makefile.in ${WORKDIR}/samhain-3.1.5
	cp -f sample.c ${WORKDIR}/samhain-3.1.5/src

	sed -i "s/\/\*\@i\@\*\/VA_COPY(vl2, vl)/va_copy(vl2, vl)/" ${WORKDIR}/samhain-3.1.5/src/sh_error.c
	cd ${WORKDIR}/samhain-3.1.5
	./configure --enable-network=client --with-log-file=/dev/null --enable-mounts-check --enable-process-check --enable-port-check --disable-dnmalloc --enable-logfile-monitor --with-pid-file=/tmp/samhain.pid

}

do_install () {
	install -d ${D}${sbindir}
	install -m 0700 ${WORKDIR}/samhain-3.1.5/samhain ${D}${sbindir}/
	install -m 0700 ${WORKDIR}/samhain-3.1.5/samhain_setpwd ${D}${sbindir}/
        # Pre condition for Samhain execution 
	install -d 0444 ${D}/var/samhain

        # Generic Samhain config file
	install -d ${D}${sysconfdir}
        install -m 0700 ${WORKDIR}/samhainrc ${D}${sysconfdir}/

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/samhain-3.1.5/samhain.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/samhain-3.1.5/samhain-precheck.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/samhain-3.1.5/samhain.path ${D}${systemd_unitdir}/system
        
        install -d ${D}${base_libdir}/rdk
        install -m 0755 ${WORKDIR}/samhain-3.1.5/samhain_starter.sh ${D}${base_libdir}/rdk

}

do_install_append_hybrid() {
        sed -i -e 's|checkmount=/mnt/memory|&\n    checkmount=/etc/snmp/snmpd.conf|g'  ${D}${sysconfdir}/samhainrc
        sed -i -e 's|checkmount=/mnt/memory|&\n    checkmount=/etc/udhcpc.vendor_specific|g'  ${D}${sysconfdir}/samhainrc
        sed -i -e 's|checkmount=/mnt/memory|&\n    checkmount=/etc/dibbler|g'  ${D}${sysconfdir}/samhainrc
}


