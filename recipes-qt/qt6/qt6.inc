include recipes-qt/qt6/qt6-ptest.inc

PACKAGECONFIG[sbom] = "-DQT_GENERATE_SBOM=ON ,-DQT_GENERATE_SBOM=OFF,"
PACKAGECONFIG[sbom-spdx-json] = "\
    -DQT_SBOM_REQUIRE_GENERATE_SPDX_V2_JSON=ON -DQT_SBOM_VERIFY_SPDX_V2=ON -DQT_SBOM_PYTHON_INTERP=${@os.path.dirname(d.getVar('PYTHON'))},\
    -DQT_SBOM_GENERATE_SPDX_V2_JSON=OFF,\
    python3-spdx-tools-native python3-booleanpy-native \
"
PACKAGECONFIG[sbom-cyclonedx] = "\
    -DQT_SBOM_REQUIRE_GENERATE_CYDX_V1_6=ON -DQT_SBOM_VERIFY_CYDX_V1_6=ON -DQT_SBOM_PYTHON_INTERP=${@os.path.dirname(d.getVar('PYTHON'))},\
    -DQT_SBOM_GENERATE_CYDX_V1_6=OFF,\
    python3-cyclonedx-python-lib-native \
    python3-packageurl-python-native \
    python3-license-expression-native \
"
PACKAGECONFIG:append:class-target = "\
    sbom \
    ${@'sbom-spdx-json' if bb.utils.to_boolean(d.getVar('QT_ENABLE_SBOM_SPDX_JSON')) else ''} \
    ${@'sbom-cyclonedx' if bb.utils.to_boolean(d.getVar('QT_ENABLE_SBOM_CYCLONEDX')) else ''} \
"

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

ENABLE_QMLCOMPILER ?= "${QT_COMMERCIAL_MODULES}"
python __anonymous() {
    if (d.getVar('QT_EDITION', True) != 'commercial' or
        d.getVar('ENABLE_QMLCOMPILER', True) == '0' or
        d.getVar('BPN', True) == 'qmlcompilerplus' or
        d.getVar('CLASSOVERRIDE', True) != 'class-target'):
        return
    if bb.utils.contains('DEPENDS', "qtdeclarative-native", True, False, d):
        d.appendVar("DEPENDS", " qmlcompilerplus-native")
}

SYSROOT_DIRS += "${QT6_INSTALL_BINDIR} ${QT6_INSTALL_LIBEXECDIR}"

PACKAGE_BEFORE_PN = "${PN}-qmlplugins ${PN}-tools ${PN}-plugins"

ALLOW_EMPTY:${PN} = "1"
ALLOW_EMPTY:${PN}-plugins = "1"
ALLOW_EMPTY:${PN}-qmlplugins = "1"

RRECOMMENDS:${PN} = " \
    ${PN}-plugins \
    ${PN}-qmlplugins \
"
RRECOMMENDS:${PN}:class-native = ""

RRECOMMENDS:${PN}-dev += " \
    ${PN}-staticdev \
"

FILES:${PN}-qmlplugins = " \
    ${QT6_INSTALL_QMLDIR} \
"

FILES:${PN}-tools = " \
    ${QT6_INSTALL_BINDIR} \
    ${QT6_INSTALL_LIBEXECDIR} \
"

FILES:${PN}-plugins = " \
    ${QT6_INSTALL_PLUGINSDIR}/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*${SOLIBSDEV} \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*/*${SOLIBSDEV} \
"
FILES:${PN}-plugins:mingw32 = " \
    ${QT6_INSTALL_PLUGINSDIR}/*/*.dll \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*.dll \
    ${QT6_INSTALL_PLUGINSDIR}/*/*/*/*.dll \
"

FILES:${PN} += " \
    ${QT6_INSTALL_LIBDIR}/lib*${SOLIBS} \
"

FILES:${PN}-dev += " \
    ${QT6_INSTALL_DESCRIPTIONSDIR} \
    ${QT6_INSTALL_DOCDIR} \
    ${QT6_INSTALL_INCLUDEDIR} \
    ${QT6_INSTALL_LIBDIR}/lib*${SOLIBSDEV} \
    ${QT6_INSTALL_LIBDIR}/*.prl \
    ${QT6_INSTALL_LIBDIR}/*.la \
    ${QT6_INSTALL_LIBDIR}/cmake \
    ${QT6_INSTALL_ARCHDATADIR}/metatypes \
    ${QT6_INSTALL_LIBDIR}/pkgconfig \
    ${QT6_INSTALL_SBOMDIR} \
    ${QT6_INSTALL_MKSPECSDIR} \
    ${QT6_INSTALL_QMLDIR}/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/*.qmltypes \
    ${QT6_INSTALL_QMLDIR}/*/designer \
    ${QT6_INSTALL_QMLDIR}/*/*/designer \
    ${QT6_INSTALL_QMLDIR}/*/*/*/designer \
"

FILES:${PN}-staticdev += " \
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
    ${QT6_INSTALL_PLUGINSDIR}/*/objects* \
    ${QT6_INSTALL_QMLDIR}/*/objects*/ \
    ${QT6_INSTALL_QMLDIR}/*/*/objects*/ \
    ${QT6_INSTALL_QMLDIR}/*/*/*/objects*/ \
    ${QT6_INSTALL_QMLDIR}/*/*/*/*/objects*/ \
"
