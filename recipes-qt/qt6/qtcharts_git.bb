LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase"

PACKAGECONFIG ?= "qml"
PACKAGECONFIG[qml] = ",,qtdeclarative"

SRCREV = "30ea03870400e11c852277ee5c4c0f2a31517020"
