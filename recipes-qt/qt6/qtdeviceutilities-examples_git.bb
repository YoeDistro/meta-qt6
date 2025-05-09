LICENSE = "The-Qt-Company-Commercial | GPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
"

inherit qt6-cmake

QT_MODULE = "qtdeviceutilities"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS += "\
    qtdeclarative-native \
    qtdeviceutilities \
"
RDEPENDS:${PN} = "qtvirtualkeyboard"
