LICENSE = "The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc
include recipes-qt/qt6/qt6-commercial.inc

DEPENDS += "qtbase qtdeclarative qtdeclarative-native"

FILES:${PN} += "${QT6_INSTALL_DATADIR}/qtinsight"

# error: unexpected reloc 3 against global symbol qt_version_tag without base register
# in object file when generating a position-independent output file
QT_PTEST_ENABLED:x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', '0', \
    bb.utils.contains('DISTRO_FEATURES', 'ptest', '1', '0', d), d)}"
