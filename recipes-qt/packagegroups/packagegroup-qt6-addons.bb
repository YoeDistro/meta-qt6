DESCRIPTION = "Qt6 addon modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d', '', d)} \
    qt5compat \
    qtcharts \
    qtcoap \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdatavis3d', '', d)} \
    qtdeviceutilities \
    qtgraphicaleffects \
    qtimageformats \
    qtlottie \
    qtmqtt \
    qtnetworkauth \
    qtopcua \
    qtquick3d \
    qtquicktimeline \
    qtscxml \
    qtserialbus \
    qtserialport \
    qtshadertools \
    qtspeech \
    qtsvg \
    qttranslations \
    qtvirtualkeyboard \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
    qtwebsockets \
"
