FILESEXTRAPATHS_append := "${THISDIR}/files:"
SRC_URI_append =" file://breakpad-ucontext.patch"
SRC_URI_append_dunfell =" file://src-client-linux-handler-exception_handler.cc-rename.patch"
SRC_URI_append =" file://breakpad_processname.patch"
