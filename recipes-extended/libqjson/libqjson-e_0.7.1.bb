SUMMARY = "QJson is a qt-based library that maps JSON data to QVariant objects"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

PR = "r0"

SRC_URI = "https://github.com/flavio/qjson/archive/${PV}.tar.gz"
S = "${WORKDIR}/qjson-${PV}/"

inherit cmake_qt5 cmake pkgconfig

EXTRA_OECMAKE = "-DQT_LIBRARY_DIR=${OE_QMAKE_LIBDIR_QT} \
                 -DQT_INSTALL_LIBS=${OE_QMAKE_LIBDIR_QT} \
                 -DQT_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT} \
                 -DQT_HEADERS_DIR=${OE_QMAKE_INCDIR_QT} \
                 -DQT_QMAKE_EXECUTABLE=${OE_QMAKE_QMAKE} \
                 -DQT_QTCORE_INCLUDE_DIR=${OE_QMAKE_INCDIR_QT}/QtCore \
                 -DQT_QTCORE_LIBRARY_RELEASE=${STAGING_LIBDIR}/libQtCoreE.so \
                "

SRC_URI[md5sum] = "634ac0c12f1112123921b7d19aeec8fa"
SRC_URI[sha256sum] = "978148abe20e4c3a12d60b14f8729fb14d6a962e6ed93efb115db71806456e50"
