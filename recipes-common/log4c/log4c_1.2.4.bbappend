
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://disable-log4crc-version-check.patch"
SRC_URI_append = " file://2001_log4c_log_rotation.patch"

# Force use of log4c's builtin yacc/lex code for parsing the log4crc config
# file instead of linking with Expat. This is a temporary workaround for issues
# seen with the RDK default log4crc config files, which contain invalid XML (ie
# sequences of '-' characters within XML comments). Expat generates errors when
# parsing these invalid config files but the log4crc builtin XML parser is more
# forgiving...

PACKAGECONFIG_remove = "expat"
