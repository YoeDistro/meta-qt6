LICENSE = "BSD-3-Clause & ( GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://src/3rdparty/assimp/src/LICENSE;md5=d9d5275cab4fb13ae624d42ce64865de \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

ASSIMP_BRANCH = "qt6_assimp"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=qtquick3d-assimp;branch=${ASSIMP_BRANCH};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native qtquick3d-native"

BBCLASSEXTEND = "native nativesdk"

PACKAGECONFIG:class-target ?= "qtquicktimeline"
PACKAGECONFIG[qtquicktimeline] = ",,qtquicktimeline"
PACKAGECONFIG[system-assimp] = "-DFEATURE_system_assimp=ON,-DFEATURE_system_assimp=OFF,assimp"

FILES:${PN}-qmlplugins += " \
  ${QT6_INSTALL_QMLDIR}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_FORMAT = "qtquick3d_qtquick3d-assimp"
