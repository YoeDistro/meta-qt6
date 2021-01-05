LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += " \
    git://github.com/assimp/assimp.git;name=assimp;branch=assimp_5.0_release;protocol=https;destsuffix=git/src/3rdparty/assimp/src \
    file://0001-CMake-allow-tools-build-without-opengl.patch \
"

DEPENDS = "qtbase qtdeclarative qtshadertools qtshadertools-native qtquick3d-native"

BBCLASSEXTEND =+ "native nativesdk"

PACKAGECONFIG ??= ""
PACKAGECONFIG[system-assimp] = "-DFEATURE_system-assimp=ON,-DFEATURE_system-assimp=OFF,assimp"

FILES_${PN}-qmlplugins += " \
  ${QT6_INSTALL_QMLDIR}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_FORMAT = "qtquick3d_assimp"
SRCREV_qtquick3d = "55ad426468f82fc65e0f571cc72cd283d7568d84"
SRCREV_assimp = "8f0c6b04b2257a520aaab38421b2e090204b69df"
