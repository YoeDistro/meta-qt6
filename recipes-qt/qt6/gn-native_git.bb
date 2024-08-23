require recipes-qt/qt6/qtwebengine.inc

inherit native

OECMAKE_SOURCEPATH = "${S}/src/gn"
OECMAKE_TARGET_COMPILE = "gn"

SRC_URI += "file://0001-Pass-no-static-libstdc-to-gen.py.patch"

cmake_do_install() {
    eval DESTDIR='${D}' ${CMAKE_VERBOSE} cmake --install '${B}'
}

INSANE_SKIP:${PN} += "already-stripped"
