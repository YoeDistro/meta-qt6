DESCRIPTION = "Qt6 essential modules"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    qtbase \
    qtdeclarative \
    qtquickcontrols2 \
    qttools \
    qttranslations-qtbase \
    qttranslations-qtdeclarative \
    qttranslations-qtquickcontrols2 \
"
