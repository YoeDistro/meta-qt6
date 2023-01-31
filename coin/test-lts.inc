python __anonymous () {
    modules = [
        'gn', 'qt3d', 'qt5compat', 'qtapplicationmanager', 'qtbase', 'qtcharts',
        'qtcoap', 'qtconnectivity', 'qtdatavis3d', 'qtdeclarative', 'qtdeviceutilities', 'qtdoc',
        'qtgraphs', 'qtgrpc', 'qthttpserver', 'qtimageformats', 'qtinterfaceframework', 'qtlanguageserver',
        'qtlocation', 'qtlottie', 'qtmqtt', 'qtmultimedia', 'qtnetworkauth', 'qtopcua', 'qtpdf',
        'qtpositioning', 'qtquick3d', 'qtquick3dphysics', 'qtquicktimeline', 'qtremoteobjects',
        'qtscxml', 'qtsensors', 'qtserialbus', 'qtserialport', 'qtshadertools', 'qtspeech',
        'qtsvg', 'qttools', 'qttranslations', 'qtvirtualkeyboard', 'qtwayland', 'qtwebchannel',
        'qtwebengine', 'qtwebsockets', 'qtwebview', 'python3-pyside6', 'python3-shiboken6'
    ]

    module = d.getVar('BPN')
    if module in modules:
        d.setVar('QT_GIT', d.getVar('QT_COMMERCIAL_GIT'))
        d.setVar('QT_GIT_PROTOCOL', d.getVar('QT_COMMERCIAL_GIT_PROTOCOL'))
        d.setVar('QT_MODULE_REPO', 'tqtc-%s.git' % d.getVar('QT_MODULE'))
        d.setVar('QT_MODULE_BRANCH', 'tqtc/lts-%s-opensource' % d.getVar('QT_MODULE_BRANCH'))
}
