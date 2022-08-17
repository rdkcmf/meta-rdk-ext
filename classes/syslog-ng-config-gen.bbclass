SUMMARY = "Generate a meta information of syslog-ng configuration details for the recipe"

python do_write_metadata_syslog_ng() {

    import os
    metadata_dir = d.expand('${D}${sysconfdir}')  + "/syslog-ng/metadata/"
    filter_dir = d.expand('${D}${sysconfdir}')  + "/syslog-ng/filter/"
    config_file = metadata_dir + d.getVar('PN', True) + ".metadata"
    filter_file = filter_dir + d.getVar('PN', True) + ".filter"
    if not os.path.exists(metadata_dir):
        os.makedirs(metadata_dir)
    if not os.path.exists(filter_dir):
        os.makedirs(filter_dir)
    if d.getVar('SYSLOG-NG_FILTER', True) != None:
        with open(filter_file, 'w') as filterfile:
            with open(config_file, 'w') as conf:
                filter_list = d.getVar('SYSLOG-NG_FILTER', True).split()
                for filter in filter_list:
                    filterfile.write("%s\n" % (filter))
                    service_tag = 'SYSLOG-NG_SERVICE_' + filter
                    if d.getVar(service_tag, True) != None:
                        services = d.getVar(service_tag, True).split()
                        for serv in services:
                            conf.write("SYSLOG-NG_SERVICE_%s = %s\n" % (filter,serv))
                    program_tag = 'SYSLOG-NG_PROGRAM_' + filter
                    if d.getVar(program_tag, True) != None:
                        program = d.getVar(program_tag, True).split()
                        for prog in program:
                            conf.write("SYSLOG-NG_PROGRAM_%s = %s\n" % (filter,prog))
                    destination_tag = 'SYSLOG-NG_DESTINATION_' + filter
                    if d.getVar(destination_tag, True) != None:
                        destination = d.getVar(destination_tag, True).split()
                        for dest in destination:
                            conf.write("SYSLOG-NG_DESTINATION_%s = %s\n" % (filter,dest))
                    lograte_tag = 'SYSLOG-NG_LOGRATE_' + filter
                    if d.getVar(lograte_tag, True) != None:
                        lograte = d.getVar(lograte_tag, True).split()
                        for logging in lograte:
                            conf.write("SYSLOG-NG_LOGRATE_%s = %s\n" % (filter,logging))
                conf.close()
            filterfile.close()

}


python() {
    if bb.utils.contains('DISTRO_FEATURES', 'syslog-ng', True, False, d):
        bb.build.addtask("write_metadata_syslog_ng", "do_package", "do_install", d)
}

FILES_${PN} += "${@bb.utils.contains('DISTRO_FEATURES','syslog-ng',' ${sysconfdir}/syslog-ng/* ','',d)}" 

