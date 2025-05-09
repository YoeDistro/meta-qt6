LICENSE = "(The-Qt-Company-Commercial | BSD-3-Clause) & Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSES/Apache-2.0.txt;md5=b4c615f64dff32f71eeed614d13dfd4c \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
"

inherit qt6-cmake

QT_MODULE = "qtconnectivity"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS += "\
    qtconnectivity \
    qtdeclarative \
    qtdeclarative-native \
"
