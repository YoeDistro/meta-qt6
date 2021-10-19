LICENSE = "BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://src/3rdparty/chromium/LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
    file://LICENSE.LGPL3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

inherit qt6-cmake pkgconfig

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

inherit gettext
inherit perlnative

inherit ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-python2", "pythonnative", "", d)}

python() {
    if 'meta-python2' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('Requires meta-python2 to be present.')
}

# we don't want gettext.bbclass to append --enable-nls
def gettext_oeconf(d):
    return ""

QT_MODULE = "qtwebengine"
QT_MODULE_BRANCH_CHROMIUM = "90-based"

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
