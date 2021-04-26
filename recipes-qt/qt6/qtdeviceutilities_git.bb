LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504"

inherit qt6-cmake
include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-git.inc

QT_MODULE_BRANCH = "6.1"

DEPENDS = "qtbase qtdeclarative qtdeclarative-native qtvirtualkeyboard"
RDEPENDS_${PN} = "connman"

SRCREV = "57c420b461b35b89cd9866c7ade42c1f6c51e583"
