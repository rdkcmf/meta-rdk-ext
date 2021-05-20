SUMMARRY = "spawn-fcgi is used to spawn FastCGI applications"
HOMEPAGE = "http://redmine.lighttpd.net/projects/spawn-fcgi"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=e4dac5c6ab169aa212feb5028853a579"

SRC_URI = "http://www.lighttpd.net/download/spawn-fcgi-${PV}.tar.gz"
SRC_URI[md5sum] = "6d75f9e9435056fa1e574d836d823cd0"
SRC_URI[sha256sum] = "7507a822995731629149040ee5686279b7582ae46f0296efd64b542ef052a90e"

inherit autotools

