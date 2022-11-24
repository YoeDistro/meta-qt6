LICENSE = "GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://src/3rdparty/chromium/LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
    file://LICENSE.FDL;md5=f70ee9a6c44ae8917586fea34dff0ab5 \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

inherit gettext
inherit perlnative
inherit python3native

# we don't want gettext.bbclass to append --enable-nls
def gettext_oeconf(d):
    return ""

QT_MODULE = "qtwebengine"
QT_MODULE_BRANCH_CHROMIUM = "94-based"

FILESEXTRAPATHS:prepend := "${THISDIR}/qtwebengine:"

SRC_URI += " \
    ${QT_GIT}/${QT_GIT_PROJECT}/qtwebengine-chromium.git;name=qtwebengine-chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty \
    file://0001-CMake-use-generated-yocto-toolchains.patch \
    file://0002-Enable-examples.patch \
"

SRC_URI += " \
    file://chromium/0001-v8-qemu-wrapper.patch;patchdir=src/3rdparty \
    file://chromium/0002-chromium-workaround-for-too-long-.rps-file-name.patch;patchdir=src/3rdparty \
"

SRCREV_FORMAT = "qtwebengine_qtwebengine-chromium"
