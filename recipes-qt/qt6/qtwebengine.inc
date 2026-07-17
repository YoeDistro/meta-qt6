LICENSE = "Apache-2.0 AND BSD-3-Clause AND MIT AND (GFDL-1.3-no-invariants-only AND LGPL-2.0-or-later AND (GPL-2.0-only OR GPL-3.0-only OR LGPL-3.0-only) AND GPL-3.0-only WITH Qt-GPL-exception-1.0 OR LicenseRef-The-Qt-Company-Commercial)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/Apache-2.0.txt;md5=b4c615f64dff32f71eeed614d13dfd4c \
    file://LICENSES/GFDL-1.3-no-invariants-only.txt;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://LICENSES/GPL-2.0-only.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LGPL-2.0-or-later.txt;md5=efce6405b860a099130379f50121ec8b \
    file://LICENSES/LGPL-3.0-only.txt;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/MIT.txt;md5=3605d54ecceddcd50962eb89318779ec \
    file://LICENSES/Qt-GPL-exception-1.0.txt;md5=9a13522cd91a88fba784baf16ea66af8 \
    file://LICENSE.Chromium;md5=d64fde5d347c3a68afe70f5e2e31fe83 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

inherit gettext
inherit perlnative
inherit python3native

inherit features_check
REQUIRED_DISTRO_FEATURES = "opengl"

# we don't want gettext.bbclass to append --enable-nls
def gettext_oeconf(d):
    return ""

QT_MODULE = "qtwebengine"
QT_MODULE_BRANCH_CHROMIUM = "140-based"

FILESEXTRAPATHS:prepend := "${THISDIR}/qtwebengine:"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtwebengine-chromium.git;name=qtwebengine-chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};protocol=${QT_GIT_PROTOCOL};destsuffix=${BB_GIT_DEFAULT_DESTSUFFIX}/src/3rdparty \
    file://0001-CMake-use-generated-yocto-toolchains.patch \
"

SRC_URI += " \
    file://chromium/0001-v8-qemu-wrapper.patch;patchdir=src/3rdparty \
    file://chromium/0002-Remove-the-GN-settings-done-for-clang-that-conflict-.patch;patchdir=src/3rdparty \
"
SRCREV_FORMAT = "qtwebengine_qtwebengine-chromium"
