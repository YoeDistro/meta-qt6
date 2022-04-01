LICENSE = "GPL-3.0-only | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

QT_GIT_PROJECT = "qt-labs"
QT_MODULE_BRANCH = "dev"

do_configure:prepend() {
    sed -i -e '/cmake_minimum_required/s/3.20/3.16/' ${S}/CMakeLists.txt
}

DEPENDS += "qtbase qtdeclarative qtdeclarative-native"

