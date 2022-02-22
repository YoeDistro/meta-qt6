DESCRIPTION = "Qt component for application lifecycle management"
LICENSE = "(GFDL-1.3 & The-Qt-Company-GPL-Exception-1.0 & (LGPL-3.0-only | GPL-2.0-or-later)) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase qtdeclarative libyaml libarchive qtapplicationmanager-native"
RDEPENDS:${PN}:class-target = "libcrypto ${PN}-tools"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'multi-process', '', d)}"

PACKAGECONFIG[tools-only] = "-DFEATURE_am_tools_only=ON, -DFEATURE_am_tools_only=OFF"
PACKAGECONFIG[multi-process] = "-DFEATURE_am_multi_process=ON, -DFEATURE_am_multi_process=OFF, qtwayland qtwayland-native"

PACKAGECONFIG:class-native ??= "tools-only"
PACKAGECONFIG:class-nativesdk ??= "${PACKAGECONFIG:class-native}"

BBCLASSEXTEND = "nativesdk native"
