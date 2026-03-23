IMAGE_CLASSES += "testimage"
QB_MEM = "-m 8192"
TEST_SUITES = "ssh ping parselogs ptest"
IMAGE_FEATURES += "ssh-server-dropbear"
IMAGE_INSTALL:append = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-ptest', '', d)} \
    qt5compat-ptest \
    qtbase-ptest \
    qtcharts-ptest \
    qtcoap-ptest \
    qtconnectivity-ptest \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdatavis3d-ptest', '', d)} \
    qtdeclarative-ptest \
    qtdeviceutilities-ptest \
    qtgraphs-ptest \
    qtgrpc-ptest \
    qthttpserver-ptest \
    qtimageformats-ptest \
    qtinterfaceframework-ptest \
    qtlocation-ptest \
    qtlottie-ptest \
    qtmqtt-ptest \
    qtmultimedia-ptest \
    qtnetworkauth-ptest \
    qtopcua-ptest \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtpdf-ptest', '', d)}
    qtpositioning-ptest \
    qtquick3d-ptest \
    ${@bb.utils.contains('QTQUICK3DPHYSICS_SUPPORTED', '1', 'qtquick3dphysics-ptest', '', d)}
    qtquickdesigner-components-ptest \
    qtquicktimeline-ptest \
    qtremoteobjects-ptest \
    qtscxml-ptest \
    qtsensors-ptest \
    qtserialbus-ptest \
    qtserialport-ptest \
    qtshadertools-ptest \
    qtspeech-ptest \
    qtsvg-ptest \
    qttools-ptest \
    qtvirtualkeyboard-ptest \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland-ptest', '', d)} \
    qtwebchannel-ptest \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtwebengine-ptest', '', d)}
    qtwebsockets-ptest \
    ${@bb.utils.contains('QTWEBENGINE_SUPPORTED', '1', 'qtwebview-ptest', '', d)}
    ${@bb.utils.contains('QT_COMMERCIAL_MODULES', '1', '\
        qmlcompilerplus-ptest \
        qtinsighttracker-ptest \
        qtvncserver-ptest \
        ', '', d)} \
"
