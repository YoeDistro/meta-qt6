LICENSE = "The-Qt-Company-Commercial | (GPL-3.0-only & Qt-GPL-exception-1.0) & (LGPL-3.0-only | GPL-2.0-only | GPL-3.0-only)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/GFDL-1.3-no-invariants-only.txt;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://LICENSES/GPL-2.0-only.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LGPL-3.0-only.txt;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/Qt-GPL-exception-1.0.txt;md5=9a13522cd91a88fba784baf16ea66af8 \
"

python() {
    if 'clang-layer' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('Requires meta-clang to be present.')
}

inherit setuptools3
inherit qt6-cmake

require recipes-qt/qt6/qt6-git.inc

QT_GIT_PROJECT = "pyside"
QT_MODULE = "pyside-setup"
QT_MODULE_BRANCH = "dev"

FILESEXTRAPATHS:prepend := "${THISDIR}/pyside6:"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DBUILD_TESTS=FALSE"
EXTRA_OECMAKE:append:class-target = " -DQFP_SHIBOKEN_HOST_PATH=${STAGING_EXECPREFIXDIR} -DQFP_PYTHON_HOST_PATH=${PYTHON}"

INSANE_SKIP:${PN} += "already-stripped"
