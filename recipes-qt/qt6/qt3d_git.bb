LICENSE = "BSD-3-Clause & ( GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.FDL;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
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
