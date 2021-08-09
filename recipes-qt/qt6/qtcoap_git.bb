LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase"

PACKAGECONFIG[examples] = "-DQT_BUILD_EXAMPLES=ON,-DQT_BUILD_EXAMPLES=OFF,qtdeclarative"

SRCREV = "7b6ddfc3409fc6d1985a4043073fb747718dd907"
