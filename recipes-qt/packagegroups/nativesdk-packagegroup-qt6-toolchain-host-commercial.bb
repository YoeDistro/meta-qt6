DESCRIPTION = "Qt6 development host commercial packages"
LICENSE = "MIT"

inherit packagegroup nativesdk

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} += " \
    nativesdk-qmlcompilerplus-dev \
    nativesdk-qmlcompilerplus-tools \
"
