LICENSE = "(The-Qt-Company-Commercial | BSD-3-Clause) & Apache-2.0 & LicenseRef-Tango-Icons-Public-Domain & MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSES/Apache-2.0.txt;md5=b4c615f64dff32f71eeed614d13dfd4c \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/LicenseRef-Tango-Icons-Public-Domain.txt;md5=b66026716fdf499434f80b11851a6cdd \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/MIT.txt;md5=3605d54ecceddcd50962eb89318779ec \
"
NO_GENERIC_LICENSE[LicenseRef-Tango-Icons-Public-Domain] = "LICENSES/LicenseRef-Tango-Icons-Public-Domain.txt"

inherit qt6-cmake
inherit features_check

REQUIRED_DISTRO_FEATURES = "opengl"

COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE:libc-musl = "(-)"
COMPATIBLE_MACHINE:aarch64 = "(.*)"
COMPATIBLE_MACHINE:armv6 = "(.*)"
COMPATIBLE_MACHINE:armv7a = "(.*)"
COMPATIBLE_MACHINE:armv7ve = "(.*)"
COMPATIBLE_MACHINE:x86-64 = "(.*)"

QT_MODULE = "qtwebengine"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS += "\
    qtdeclarative-native \
    qtwebengine \
"
