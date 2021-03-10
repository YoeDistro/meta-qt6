DESCRIPTION = "Qt6 development packages"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"


RDEPENDS_${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-dev', '', d)} \
    qt5compat-dev \
    qtbase-dev \
    qtcharts-dev \
    qtcoap-dev \
    qtdeclarative-dev \
    qtgraphicaleffects-dev \
    qtimageformats-dev \
    qtmqtt-dev \
    qtnetworkauth-dev \
    qtopcua-dev \
    qtquick3d-dev \
    qtquickcontrols2-dev \
    qtquicktimeline-dev \
    qtscxml-dev \
    qtserialbus-dev \
    qtserialport-dev \
    qtshadertools-dev \
    qtspeech-dev \
    qtsvg-dev \
    qttools-dev \
    qttranslations-dev \
    qtvirtualkeyboard-dev \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland-dev', '', d)} \
    qtwebsockets-dev \
"
