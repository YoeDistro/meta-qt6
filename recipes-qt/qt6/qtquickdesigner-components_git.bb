LICENSE = "GPL-3.0-only OR LicenseRef-The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

QT_GIT_PROJECT = "qt-labs"
QT_MODULE_BRANCH = "dev"

DEPENDS += "qtbase qtdeclarative qtdeclarative-native"

# QDS-16228
QT_ENABLE_SBOM_SPDX_JSON = "0"
QT_ENABLE_SBOM_CYCLONEDX = "0"
