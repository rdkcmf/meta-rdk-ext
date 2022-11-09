FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://diagnostics.patch"

EXTRA_C_FLAGS = "-D_FILE_OFFSET_BITS=64 -Wall -Wextra"
CFLAGS += "${EXTRA_C_FLAGS}"
CXXFLAGS += "${EXTRA_C_FLAGS}"

