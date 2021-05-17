LICENSE = "LGPL-3.0 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase"

PACKAGECONFIG ?= "qml"
PACKAGECONFIG[examples] = "-DQT_BUILD_EXAMPLES=ON,-DQT_BUILD_EXAMPLES=OFF,qtscxml-native"
PACKAGECONFIG[qml] = ",,qtdeclarative qtdeclarative-native"

BBCLASSEXTEND =+ "native nativesdk"

SRCREV = "1b5bdca80249782a84fbbd3cbd37c0bf0b772467"
