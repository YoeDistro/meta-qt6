SUMMARY = "Python Qt6 Bindings"
HOMEPAGE = "https://www.riverbankcomputing.com/software/pyqt"
SECTION = "devel/python"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

inherit pypi python3targetconfig python3-dir qt6-qmake qt6-paths

PYPI_PACKAGE = "PyQt6"

SRC_URI[sha256sum] = "9f158aa29d205142c56f0f35d07784b8df0be28378d20a97bcda8bd64ffd0379"

S = "${WORKDIR}/PyQt6-${PV}"
B = "${S}/build"

DEPENDS += " \
    qtbase \
    qtdeclarative \
    sip (>= 6.7.12) \
    sip-native (>= 6.7.12) \
    python3 \
    python3-pyqt6-sip-native \
    python3-pyqt6-sip \
    python3-packaging-native \
    python3-tomli-native \
    python3-pyqt-builder-native \
    python3-ply \
    python3-ply-native \
"

RDEPENDS:${PN} += " \
    qtbase \
    qtdeclarative \
    python3-core \
    python3-pyqt6-sip \
"

# Disable support of 128bit ints and add path to Python.h
CXXFLAGS += " -DQT_NO_INT128 -I${PYTHON_INCLUDE_DIR}"

EXTRA_OEMAKE = "INSTALL_ROOT=${D}"

DISABLED_FEATURES = " \
    PyQt_Desktop_OpenGL \
    PyQt_Accessibility \
    PyQt_SessionManager \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '', 'PyQt_OpenGL', d)} \
"

PYQT_MODULES = " \
    QtCore \
    QtGui \
    QtNetwork \
    QtXml \
    QtNetwork \
    QtQml \
    QtSql \
"

do_configure() {
    local i
    local extra_args

    for i in ${DISABLED_FEATURES}; do
        extra_args="${extra_args} --disabled-feature=${i}"
    done

    for i in ${PYQT_MODULES}; do
        extra_args="${extra_args} --enable=${i}"
    done

    cd ${S}
    sip-build \
        --verbose \
        --confirm-license \
        --scripts-dir="${bindir}" \
        --build-dir="${B}" \
        --target-dir="${PYTHON_SITEPACKAGES_DIR}" \
        --no-make \
        --qmake=${OE_QMAKE_QMAKE} \
        --pep484-pyi \
        --no-dbus-python \
        ${extra_args}

    QMAKE_PROFILES=${B}/PyQt6.pro
}

do_compile:append() {
    sed -i "s,${STAGING_DIR_TARGET},," ${B}/inventory.txt
}

do_install:append() {
    sed -i "s,exec .*nativepython3,exec ${bindir}/python3," ${D}/${bindir}/*
}

# fix buildpaths warnings
pyqt_fix_sources() {
    find ${PKGD}/usr/src/debug/${PN} -type f -exec sed -i "s,\(${B}\|${S}\),/usr/src/debug/${PN}/${PV}-${PR},g" {} \;
}
PACKAGESPLITFUNCS:prepend = "pyqt_fix_sources"

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR} ${OE_QMAKE_PATH_PLUGINS}"
