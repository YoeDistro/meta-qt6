LICENSE = "(The-Qt-Company-Commercial | BSD-3-Clause) & CC0-1.0 & MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/CC0-1.0.txt;md5=65d3616852dbf7b1a6d4b53b00626032 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/MIT.txt;md5=3605d54ecceddcd50962eb89318779ec \
"

inherit qt6-cmake

QT_MODULE = "qtquick3d"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS += "\
    qtdeclarative-native \
    qtquick3d \
    qtquick3d-native \
"

