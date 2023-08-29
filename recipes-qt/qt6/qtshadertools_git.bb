LICENSE = "GFDL-1.3 & BSD-3-Clause & GPL-3.0-only | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-lts.inc
include recipes-qt/qt6/qt6.inc

DEPENDS = "qtbase qtshadertools-native"
DEPENDS:append:class-native = " spirv-tools-native"

RDEPENDS:${PN}-tools = "spirv-tools"
RDEPENDS:${PN}-tools:remove:mingw32 = "spirv-tools"

BBCLASSEXTEND = "native nativesdk"

