LICENSE = "(The-Qt-Company-Commercial | BSD-3-Clause) & CC-BY-4.0 & LicenseRef-MIRAMAR"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/CC-BY-4.0.txt;md5=ed88d31cea57b15030a1f58ceb04e0d5 \
    file://LICENSES/LicenseRef-MIRAMAR.txt;md5=6dd50bdc58b03a7976544fb939d5647b \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
"
NO_GENERIC_LICENSE[LicenseRef-MIRAMAR] = "LICENSES/LicenseRef-MIRAMAR.txt"

inherit qt6-cmake
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

QT_MODULE = "qt3d"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS = "\
    qt3d \
    qtdeclarative-native \
"
