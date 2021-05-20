LXC_PACKAGES ?= "${PN}"
LXC_TEMPLATE ?= "lxc-xre"
LXC_NAME ?= "xre"
python __anonymous() {
    if "container" in d.getVar('MACHINEOVERRIDES', True):
        d.appendVar("DEPENDS", " lxc-native")
}

lxc_postinst() {
rootDir="$D"
if type lxc-create >/dev/null 2>/dev/null; then
      mkdir -p ${rootDir}/lxc
      echo "Executing :  lxc-create -t ${rootDir}/usr/share/lxc/templates/${LXC_TEMPLATE} -n ${LXC_NAME} -P ${rootDir}/lxc"
      lxc-create -t ${rootDir}/usr/share/lxc/templates/${LXC_TEMPLATE} -n ${LXC_NAME} -P ${rootDir}/lxc
fi
}

lxc_populate_packages[vardeps] += "lxc_postinst"
lxc_populate_packages[vardepsexclude] += "OVERRIDES"

python lxc_populate_packages() {
    for pkg in d.getVar('LXC_PACKAGES', True).split():
        postinst = d.getVar('pkg_postinst_%s' % pkg, True)
        if not postinst:
            postinst = '#!/bin/sh\n'
            postinst += d.getVar('lxc_postinst', True)
            d.setVar('pkg_postinst_%s' % pkg, postinst)
}

PACKAGESPLITFUNCS_prepend = "lxc_populate_packages "
FILES_${PN} += "/lxc/*"
