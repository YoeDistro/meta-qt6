LICENSE = "The-Qt-Company-Commercial | BSD-3-Clause & (LGPL-3.0-only | GPL-2.0-only | GPL-3.0-only)"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/GPL-2.0-only.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LGPL-3.0-only.txt;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
"

inherit qt6-cmake

QT_MODULE = "qtwebchannel"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS += "\
    qtdeclarative-native \
    qtwebchannel \
    qtwebsockets \
"
