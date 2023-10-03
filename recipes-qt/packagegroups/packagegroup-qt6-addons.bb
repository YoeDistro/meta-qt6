DESCRIPTION = "Qt6 addon modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d', '', d)} \
    qt5compat \
    qtapplicationmanager \
    qtcharts \
    qtcoap \
    qtconnectivity \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdatavis3d', '', d)} \
    qtdeviceutilities \
    qtgraphs \
    qtgrpc \
    qthttpserver \
    qtimageformats \
    qtinterfaceframework \
    qtlocation \
    qtlottie \
    qtmqtt \
    qtmultimedia \
    qtnetworkauth \
    qtopcua \
    qtpositioning \
    qtquick3d \
    qtquickdesigner-components \
    qtquicktimeline \
    qtremoteobjects \
    qtscxml \
    qtsensors \
    qtserialbus \
    qtserialbus-tools \
    qtserialport \
    qtshadertools \
    qtspeech \
    qtsvg \
    qttranslations \
    qtvirtualkeyboard \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtwebchannel \
    qtwebsockets \
"

ARCHITECTURE_LIMITED_MODULES = "\
    qtquick3dphysics \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtpdf', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebview', '', d)} \
"
RDEPENDS:${PN}:append:aarch64 ="${ARCHITECTURE_LIMITED_MODULES}"
RDEPENDS:${PN}:append:arm ="${ARCHITECTURE_LIMITED_MODULES}"
RDEPENDS:${PN}:append:x86 ="${ARCHITECTURE_LIMITED_MODULES}"
RDEPENDS:${PN}:append:x86-64 ="${ARCHITECTURE_LIMITED_MODULES}"
