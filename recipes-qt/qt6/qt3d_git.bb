LICENSE = "BSD-3-Clause & ( GFDL-1.3 & BSD & ( LGPL-3.0 | GPL-2.0 ) | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL;md5=c96076271561b0e3785dad260634eaa8 \
    file://src/3rdparty/assimp/src/LICENSE;md5=d9d5275cab4fb13ae624d42ce64865de \
"

inherit qt6-cmake
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

ASSIMP_BRANCH = "qt6_assimp"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=qt3d-assimp;branch=${ASSIMP_BRANCH};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtdeclarative-native qtshadertools qtshadertools-native"

PACKAGECONFIG[system-assimp] = "-DFEATURE_qt3d_system_assimp=ON,-DQT_FEATURE_qt3d_system_assimp=OFF,assimp"
PACKAGECONFIG[qtgamepad] = ",,qtgamepad"

SRCREV_FORMAT = "qt3d_qt3d-assimp"
