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
    qtopcua-examples \
    qtpositioning-examples \
    qtquick3d-examples \
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
    qtwebsockets-examples \
"

RDEPENDS:${PN}:append:aarch64 = "\
    qtquick3dphysics-examples \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf-examples', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine-examples qtwebview-examples', '', d)} \
"
RDEPENDS:${PN}:append:arm = "\
    qtquick3dphysics-examples \
"
RDEPENDS:${PN}:append:armv6 = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf-examples', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine-examples qtwebview-examples', '', d)} \
"
RDEPENDS:${PN}:append:armv7a = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf-examples', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine-examples qtwebview-examples', '', d)} \
"
RDEPENDS:${PN}:append:armv7ve = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf-examples', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine-examples qtwebview-examples', '', d)} \
"
RDEPENDS:${PN}:append:x86 = "\
    qtquick3dphysics-examples \
"
RDEPENDS:${PN}:append:x86-64 = "\
    qtquick3dphysics-examples \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf-examples', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'webengine', 'qtwebengine-examples qtwebview-examples', '', d)} \
"

RDEPENDS:${PN}:remove:libc-musl = "qtpdf-examples qtwebengine-examples qtwebview-examples"

COMMERCIAL_EXAMPLES = " \
    qtinsighttracker-examples \
    qtvncserver-examples \
"
RDEPENDS:${PN} += "\
    ${@bb.utils.contains('QT_COMMERCIAL_MODULES', '1', '${COMMERCIAL_EXAMPLES}', '', d)} \
"

