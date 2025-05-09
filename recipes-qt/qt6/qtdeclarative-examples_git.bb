LICENSE = "(The-Qt-Company-Commercial | BSD-3-Clause) & Apache-2.0 & CC-BY-3.0 & MIT & OFL-1.1"
LIC_FILES_CHKSUM = " \
    file://LICENSES/Apache-2.0.txt;md5=b4c615f64dff32f71eeed614d13dfd4c \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/CC-BY-3.0.txt;md5=6dffb34dbf23fffe10cc646d9c030e14 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/MIT.txt;md5=3605d54ecceddcd50962eb89318779ec \
    file://LICENSES/OFL-1.1.txt;md5=e0e18125674e1542f95ea36a4a958f57 \
"

inherit qt6-cmake

QT_MODULE = "qtdeclarative"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS += "\
    qtdeclarative \
    qtdeclarative-native \
"
