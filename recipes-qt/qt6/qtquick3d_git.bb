LICENSE = "BSD-3-Clause & ( GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://src/3rdparty/assimp/src/LICENSE;md5=de63670479d9e7a0c4bbf593b7e1c1cd \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

ASSIMP_BRANCH = "qt6.1_assimp"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=assimp;branch=${ASSIMP_BRANCH};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/assimp/src \
    file://0001-CMake-allow-tools-build-without-opengl.patch \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native qtquick3d-native"

BBCLASSEXTEND =+ "native nativesdk"

PACKAGECONFIG[system-assimp] = "-DFEATURE_system_assimp=ON,-DFEATURE_system_assimp=OFF,assimp"

FILES_${PN}-qmlplugins += " \
  ${QT6_INSTALL_QMLDIR}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_FORMAT = "qtquick3d_assimp"
SRCREV_qtquick3d = "3eca5f49555bf2c37bbe071a2f8b4d6b1a973d1c"
SRCREV_assimp = "1620a72e5820d7c680f6f5da10a3481ceb4e53c2"
