CFLAGS+=-fPIC `pkg-config --cflags gstreamer-1.0 gstreamer-base-1.0 wayland-client` \

LDFLAGS+=-fPIC `pkg-config --libs gstreamer-1.0 gstreamer-base-1.0 wayland-client` \
 -lwesteros_compositor \
 -lwesteros_simpleshell_client \
 -lwesteros_simplebuffer_client \

all: libgstexternal.so

libgstexternal.so: Makefile gst_external_src.c gst_external_sink.c gst_external_plugin.c
	$(CC) -g3 $(CFLAGS) gst_external_src.c gst_external_sink.c gst_external_plugin.c $(LDFLAGS) -shared -Wl,-soname,libgstexternal.so -o libgstexternal.so

clean:
	rm -f libgstexternal.so

install:
	install -d $(DESTDIR)/usr/lib/gstreamer-1.0
	install libgstexternal.so $(DESTDIR)/usr/lib/gstreamer-1.0
