python __anonymous () {
    modules = [
        'gn', 'qt3d', 'qt5compat', 'qtapplicationmanager', 'qtbase', 'qtcharts',
        'qtcoap', 'qtconnectivity', 'qtdatavis3d', 'qtdeclarative', 'qtdeviceutilities',
        'qtimageformats', 'qtinterfaceframework', 'qtlottie', 'qtmqtt', 'qtmultimedia',
        'qtnetworkauth', 'qtopcua', 'qtpositioning', 'qtquick3d', 'qtquicktimeline',
        'qtremoteobjects', 'qtscxml', 'qtsensors', 'qtserialbus', 'qtserialport',
        'qtshadertools', 'qtsvg', 'qttools', 'qttranslations', 'qtvirtualkeyboard',
        'qtwayland', 'qtwebchannel', 'qtwebengine', 'qtwebsockets', 'qtwebview'
    ]

    for module in modules:
        for pn in [ module, '%s-native' % module, 'nativesdk-%s' % module ]:
            d.setVar('QT_GIT:pn-%s' % pn, d.getVar('QT_COMMERCIAL_GIT'))
            d.setVar('QT_GIT_PROTOCOL:pn-%s' % pn, d.getVar('QT_COMMERCIAL_GIT_PROTOCOL'))
            d.setVar('QT_MODULE_REPO:pn-%s' % pn, 'tqtc-%s.git' % d.getVar('QT_MODULE'))
            d.setVar('QT_MODULE_BRANCH:pn-%s' % pn, 'tqtc/lts-%s-opensource' % d.getVar('QT_MODULE_BRANCH'))
}
