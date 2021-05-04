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

SRCREV = "3ff6b3dc096e9fe94e4f5ff6ceb620a6b1ae334b"
