DESCRIPTION = "Qt6 addon modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} += " \
    ${@'python3-pyside6' if bb.utils.to_boolean(d.getVar('CAN_USE_PYSIDE6')) else ''} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d', '', d)} \
    qt5compat \
    qtapplicationmanager \
    qtcanvaspainter \
    qtcharts \
    qtcoap \
    qtconnectivity \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdatavis3d', '', d)} \
    qtdeviceutilities \
    qtdoc \
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
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtpdf', '', d)} \
    qtpositioning \
    qtquick3d \
    ${@bb.utils.contains('QTQUICK3DPHYSICS_SUPPORTED', '1', 'qtquick3dphysics', '', d)} \
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
    qttasktree \
    qttranslations \
    qtvirtualkeyboard \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtwebchannel \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtwebengine', '', d)} \
    qtwebsockets \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtwebview', '', d)} \
"
