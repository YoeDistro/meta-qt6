LICENSE = "The-Qt-Company-Commercial | (GPL-3.0-only & Qt-GPL-exception-1.0) & GFDL-1.3-no-invariants-only"
LIC_FILES_CHKSUM = " \
    file://LICENSES/GFDL-1.3-no-invariants-only.txt;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/Qt-GPL-exception-1.0.txt;md5=9a13522cd91a88fba784baf16ea66af8 \
"

inherit qt6-cmake features_check

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS += "qtbase qtdeclarative qtdeclarative-native"
