require python3-pyside6.inc

DEPENDS += "\
    python3-shiboken6 \
    python3-shiboken6-native \
    ${PYSIDE_QT_MODULES} \
"
PYSIDE_QT_MODULES ?= "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d', '', d)} \
    qtbase \
    qtcharts \
    qtconnectivity \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdatavis3d', '', d)} \
    qtdeclarative \
    qtdeclarative-native \
    qtgraphs \
    ${@bb.utils.contains('QT_COMMERCIAL_MODULES', '1', 'qthttpserver', '', d)} \
    qtlocation \
    qtmultimedia \
    qtnetworkauth \
    qtpositioning \
    qtquick3d \
    qtquick3d-native \
    qtremoteobjects \
    qtremoteobjects-native \
    qtscxml \
    qtscxml-native \
    qtsensors \
    qtserialbus \
    qtserialport \
    qtspeech \
    qtsvg \
    qttools \
    qtwebchannel \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtpdf', '', d)} \
    qtwebsockets \
"

OECMAKE_SOURCEPATH = "${S}/sources/pyside6"

export LLVM_INSTALL_DIR = "${STAGING_DIR_NATIVE}${exec_prefix}"

PYSIDE_COMPILER = "${HOST_SYS}-g++"
PYSIDE_COMPILER:toolchain-clang = "${HOST_SYS}-clang++"
# Workaround big.LITTLE architecture args not supported by clang
PYSIDE_COMPILER_FLAGS = "${@d.getVar('HOST_CC_ARCH') \
    .replace('cortex-a15.cortex','cortex') \
    .replace('cortex-a17.cortex','cortex') \
    .replace('cortex-a57.cortex','cortex') \
    .replace('cortex-a72.cortex','cortex') \
    .replace('cortex-a73.cortex','cortex') \
    .replace('cortex-a75.cortex','cortex') \
    .replace('cortex-a76.cortex','cortex')} \
"

EXTRA_OECMAKE += "\
    -DSTANDALONE=ON \
    -DPYSIDE_TREAT_QT_INCLUDE_DIRS_AS_NON_SYSTEM=ON \
    -DSHIBOKEN_GENERATOR_EXTRA_FLAGS='\
        --clang-options=--sysroot=${STAGING_DIR_TARGET},--target=${HOST_SYS},${@d.getVar('PYSIDE_COMPILER_FLAGS').replace(' ',',')} \
        --compiler-path=${PYSIDE_COMPILER} \
    ' \
"

FILES:${PN} += "\
    ${QT6_INSTALL_PLUGINSDIR}/designer \
"
FILES:${PN}-dev += "\
    ${datadir}/PySide6 \
"
