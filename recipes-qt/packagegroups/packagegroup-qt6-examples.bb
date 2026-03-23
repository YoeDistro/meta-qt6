DESCRIPTION = "Qt6 examples"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-examples', '', d)} \
    qt5compat-examples \
    qtapplicationmanager-examples \
    qtbase-examples \
    qtcanvaspainter-examples \
    qtcharts-examples \
    qtcoap-examples \
    qtconnectivity-examples \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdatavis3d-examples', '', d)} \
    qtdeclarative-examples \
    qtdeviceutilities-examples \
    qtdoc-examples \
    qtgraphs-examples \
    qtgrpc-examples \
    qthttpserver-examples \
    qtinterfaceframework-examples \
    qtlocation-examples \
    qtmqtt-examples \
    qtmultimedia-examples \
    qtnetworkauth-examples \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtpdf-examples', '', d)} \
    qtopcua-examples \
    qtpositioning-examples \
    qtquick3d-examples \
    ${@bb.utils.contains('QTQUICK3DPHYSICS_SUPPORTED', '1', 'qtquick3dphysics-examples', '', d)} \
    qtremoteobjects-examples \
    qtscxml-examples \
    qtsensors-examples \
    qtserialbus-examples \
    qtserialport-examples \
    qtspeech-examples \
    qttasktree-examples \
    qttools-examples \
    qtvirtualkeyboard-examples \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland-examples', '', d)} \
    qtwebchannel-examples \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtwebengine-examples', '', d)} \
    qtwebsockets-examples \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtwebview-examples', '', d)} \
"

COMMERCIAL_EXAMPLES = " \
    qtinsighttracker-examples \
    qtvncserver-examples \
"
RDEPENDS:${PN} += "\
    ${@bb.utils.contains('QT_COMMERCIAL_MODULES', '1', '${COMMERCIAL_EXAMPLES}', '', d)} \
"

