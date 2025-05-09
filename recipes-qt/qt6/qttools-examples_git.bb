LICENSE = "The-Qt-Company-Commercial | BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
"

inherit qt6-cmake

QT_MODULE = "qttools"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

SRC_URI += "file://0001-examples-don-t-track-source-path.patch"

DEPENDS += "\
    qttools \
    qttools-native \
"
