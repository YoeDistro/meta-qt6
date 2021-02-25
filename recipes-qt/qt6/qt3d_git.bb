LICENSE = "LGPL-3.0 | GPL-2.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL;md5=c96076271561b0e3785dad260634eaa8 \
"

inherit qt6-cmake
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=assimp;branch=qt6_assimp;protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native"

PACKAGECONFIG ??= ""
PACKAGECONFIG_class-target ?= "system-assimp"
PACKAGECONFIG[system-assimp] = "-DFEATURE_system_assimp=ON,-DQT_FEATURE_system_assimp=OFF,assimp"
PACKAGECONFIG[qtgamepad] = ",,qtgamepad"

SRCREV_FORMAT = "qt3d_assimp"
SRCREV_qt3d = "e046a40b74ba9d25f1c73165b74eb805e330ff8f"
SRCREV_assimp = "d71020db155e9b2d4dba5562c980c016cde77f4c"
