PACKAGECONFIG_append += " icu"
EXTRA_OECONF_remove = "--with-cairo"
EXTRA_OECONF_append  = " --with-cairo=no"
