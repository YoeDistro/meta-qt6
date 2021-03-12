inherit srcrev-update

PACKAGECONFIG[examples] = "-DQT_BUILD_EXAMPLES=ON,-DQT_BUILD_EXAMPLES=OFF,"
PACKAGECONFIG[tests] = "-DQT_BUILD_TESTS=ON,-DQT_BUILD_TESTS=OFF,"

# For qmake projects
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'examples', 'QT_BUILD_PARTS+=examples', 'QT_BUILD_PARTS-=examples', d)}"
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'tests', 'QT_BUILD_PARTS+=tests', 'QT_BUILD_PARTS-=tests', d)}"

# If Qt6 (qtbase) is machine specific, then everything will be,
# because the (initial) qtbase configuration becomes part of Qt5/qmake
python __anonymous() {
    barch = d.getVar("BUILD_ARCH", True) or ''
    tarch = d.getVar("TARGET_ARCH", True) or ''
    # do not do anything if we are building a native package
    if barch != tarch:
        tarch = d.getVar("QT_PACKAGES_ARCH", True) or ''
        if tarch:
            d.setVar("PACKAGE_ARCH", tarch)
}

# Automotically add qtdeclarative-native when recipe depends on qtdeclarative
python __anonymous() {
    if bb.utils.contains('DEPENDS', "qtdeclarative", True, False, d):
        d.appendVar("DEPENDS", " qtdeclarative-native")
}

# Many examples come with libraries installed outside of standard libdir,
# suppress QA check complaining
INSANE_SKIP_${PN}-dbg += "libdir"
INSANE_SKIP_${PN}-examples += "libdir dev-so"

SYSROOT_DIRS_append_mingw32 = " ${QT6_INSTALL_BINDIR}"

PACKAGE_BEFORE_PN = "${PN}-qmlplugins ${PN}-tools ${PN}-plugins ${PN}-examples"

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-plugins = "1"
ALLOW_EMPTY_${PN}-qmlplugins = "1"

RRECOMMENDS_${PN} = " \
    ${PN}-plugins \
    ${PN}-qmlplugins \
"
RRECOMMENDS_${PN}_class-native = ""

RRECOMMENDS_${PN}-dev += " \
    ${PN}-staticdev \
"

FILES_${PN}-qmlplugins = " \
    ${QT6_INSTALL_QMLDIR} \
"

FILES_${PN}-tools = " \
    ${QT6_INSTALL_BINDIR} \
"

FILES_${PN}-plugins = " \
    ${QT6_INSTALL_PLUGINSDIR}/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*/*${SOLIBSDEV} \
"
FILES_${PN}-plugins_mingw32 = " \
    ${QT6_INSTALL_PLUGINSDIR}/*/*.dll \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*.dll \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*/*.dll \
"

FILES_${PN} += " \
    ${QT6_INSTALL_LIBDIR}/lib*${SOLIBS} \
    ${QT6_INSTALL_LIBEXECDIR} \
"

FILES_${PN}-dev += " \
    ${QT6_INSTALL_DESCRIPTIONSDIR} \
    ${QT6_INSTALL_DOCDIR} \
    ${QT6_INSTALL_INCLUDEDIR} \
    ${QT6_INSTALL_LIBDIR}/lib*${SOLIBSDEV} \
    ${QT6_INSTALL_LIBDIR}/*.prl \
    ${QT6_INSTALL_LIBDIR}/*.la \
    ${QT6_INSTALL_LIBDIR}/cmake \
    ${QT6_INSTALL_LIBDIR}/metatypes \
    ${QT6_INSTALL_LIBDIR}/pkgconfig \
    ${QT6_INSTALL_MKSPECSDIR} \
    ${QT6_INSTALL_QMLDIR}/*/designer \
    ${QT6_INSTALL_QMLDIR}/*/*/designer \
    ${QT6_INSTALL_QMLDIR}/*/*/*/designer \
"

FILES_${PN}-staticdev += " \
    ${QT6_INSTALL_LIBDIR}/*.a \
    ${QT6_INSTALL_PLUGINSDIR}/*/*.a \
    ${QT6_INSTALL_PLUGINSDIR}/*/*.prl \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*.a \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.prl \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.a \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.prl \
    ${QT6_INSTALL_LIBDIR}/objects* \
    ${QT6_INSTALL_QMLDIR}/*/objects*/ \
    ${QT6_INSTALL_QMLDIR}/*/*/objects*/ \
    ${QT6_INSTALL_QMLDIR}/*/*/*/objects*/ \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/objects*/ \
"

# Note: INSTALL_EXAMPLESDIR is not used
FILES_${PN}-examples = " \
    ${QT6_INSTALL_EXAMPLESDIR} \
    ${prefix}/examples \
"
