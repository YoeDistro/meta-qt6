DESCRIPTION = "Qt6 development host addon packages"
LICENSE = "MIT"

# disable sanity check for allarch packagegroup
PACKAGE_ARCH = ""

inherit packagegroup nativesdk

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS:${PN} += " \
    nativesdk-qtapplicationmanager-dev \
    nativesdk-qtapplicationmanager-tools \
    nativesdk-qtgrpc-dev \
    nativesdk-qtgrpc-tools \
    nativesdk-qtlottie-dev \
    nativesdk-qtlottie-tools \
    nativesdk-qtquick3d-dev \
    nativesdk-qtquick3d-tools \
    nativesdk-qtopcua-dev \
    nativesdk-qtopcua-tools \
    nativesdk-qtremoteobjects-dev \
    nativesdk-qtremoteobjects-tools \
    nativesdk-qtscxml-dev \
    nativesdk-qtscxml-tools \
    nativesdk-qtshadertools-dev \
    nativesdk-qtshadertools-tools \
    ${FORLINUXHOST} \
"

FORLINUXHOST:mingw32 = ""
FORLINUXHOST = " \
    nativesdk-qtinterfaceframework-dev nativesdk-qtinterfaceframework-tools \
"

