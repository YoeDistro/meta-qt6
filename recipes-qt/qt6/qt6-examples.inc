FILESEXTRAPATHS:prepend := "${THISDIR}/${QT_MODULE}:"

EXTRA_OECMAKE += "\
    -DQT_BUILD_TESTS=OFF \
    -DQT_BUILD_STANDALONE_EXAMPLES=ON \
    -DQT_NO_FAKE_STANDALONE_EXAMPLE_INSTALL_PREFIX=ON \
"

# use debug sources from the module
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"
TARGET_DBGSRC_DIR = "/usr/src/debug/${QT_MODULE}/${PV}"

FILES:${PN} = " \
    ${QT6_INSTALL_EXAMPLESDIR} \
"

INSANE_SKIP:${PN} += "libdir dev-so"
INSANE_SKIP:${PN}-dbg += "libdir"
