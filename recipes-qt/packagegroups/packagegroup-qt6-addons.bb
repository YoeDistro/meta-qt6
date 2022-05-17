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
    ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'qtconnectivity-tools', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdatavis3d', '', d)} \
    qtdeviceutilities \
    qtimageformats \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-python', 'qtinterfaceframework', '', d)} \
    qtlottie \
    qtmqtt \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtmultimedia', '', d)} \
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
    qtserialport \
    qtshadertools \
    qtsvg \
    qttranslations \
    qtvirtualkeyboard \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtwebchannel \
    qtwebsockets \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine-tools', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebview', '', d)} \
"
