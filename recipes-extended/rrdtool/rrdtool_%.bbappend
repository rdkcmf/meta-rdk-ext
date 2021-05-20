PACKAGECONFIG[graph] = "--enable-rrd_graph --enable-rrdcgi,--disable-rrd_graph --disable-rrdcgi,libpng cairo pango zlib,"
PACKAGECONFIG = "perl"
# override the defaults from meta-oe
DEPENDS := "glib-2.0 libxml2"

