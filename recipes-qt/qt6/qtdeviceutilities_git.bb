LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504"

inherit qt6-cmake
include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-git.inc

QT_MODULE_BRANCH = "dev"

DEPENDS = "qtbase qtdeclarative qtdeclarative-native qtvirtualkeyboard"
RDEPENDS_${PN} = "connman"

SRCREV = "77987a087507ecccb616a1af158190ffd3082a02"
