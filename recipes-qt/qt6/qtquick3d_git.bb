LICENSE = "(The-Qt-Company-Commercial | (GPL-3.0-only & Qt-GPL-exception-1.0) & GPL-3.0-only & GFDL-1.3-no-invariants-only) & Apache-2.0 & BSD-3-Clause & BSL-1.0 & CC-BY-4.0 & MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSES/Apache-2.0.txt;md5=b4c615f64dff32f71eeed614d13dfd4c \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/BSL-1.0.txt;md5=8c92b4c255bdcce2989707d5b8a4d302 \
    file://LICENSES/CC-BY-4.0.txt;md5=ed88d31cea57b15030a1f58ceb04e0d5 \
    file://LICENSES/GFDL-1.3-no-invariants-only.txt;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/Qt-GPL-exception-1.0.txt;md5=9a13522cd91a88fba784baf16ea66af8 \
    file://src/3rdparty/assimp/LICENSE;md5=78dabdafb167945fef55b5c37ac94df3 \
    file://src/3rdparty/embree/LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://src/3rdparty/tinyexr/LICENSE;md5=27559be3d3cfab88d56b352c10fb9476 \
    file://src/3rdparty/xatlas/LICENSE;md5=8605e91c32ad7d58bd62c310eb2c3bf6 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

ASSIMP_BRANCH = "qt6_assimp"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtquick3d-assimp.git;name=qtquick3d-assimp;branch=${ASSIMP_BRANCH};protocol=${QT_GIT_PROTOCOL};destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/src/3rdparty/assimp/src \
    file://0001-Skip-embree-on-mingw.patch \
    file://0001-openxr-Recognise-riscv32-platform.patch \
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

# Needed for supporting 64bit off_t
CFLAGS:append:libc-musl = " -DIOAPI_NO_64 -D_GNU_SOURCE"

INSANE_SKIP:${PN}-ptest += "buildpaths"
