LICENSE = "BSD-3-Clause & ( GFDL-1.3 & BSD & ( LGPL-3.0 | GPL-2.0 ) | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL;md5=c96076271561b0e3785dad260634eaa8 \
    file://src/3rdparty/assimp/src/LICENSE;md5=de63670479d9e7a0c4bbf593b7e1c1cd \
"

inherit qt6-cmake
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

ASSIMP_BRANCH = "qt6.1_assimp"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=assimp;branch=${ASSIMP_BRANCH};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native"

PACKAGECONFIG ?= "system-assimp"
PACKAGECONFIG[system-assimp] = "-DFEATURE_qt3d_system_assimp=ON,-DQT_FEATURE_qt3d_system_assimp=OFF,assimp"
PACKAGECONFIG[qtgamepad] = ",,qtgamepad"

SRCREV_FORMAT = "qt3d_assimp"
SRCREV_qt3d = "699d343b022e923712259a13ac6fa237ff714379"
SRCREV_assimp = "1620a72e5820d7c680f6f5da10a3481ceb4e53c2"
