DESCRIPTION = "Qt6 development host packages"
LICENSE = "MIT"

inherit packagegroup nativesdk

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

RDEPENDS_${PN} += " \
    nativesdk-make \
    nativesdk-cmake \
    nativesdk-ninja \
    nativesdk-perl-modules \
    nativesdk-qtbase-dev \
    nativesdk-qtbase-tools \
    nativesdk-qtdeclarative-dev \
    nativesdk-qtdeclarative-tools \
    nativesdk-qtquick3d-dev \
    nativesdk-qtquick3d-tools \
    nativesdk-qttools-dev \
    nativesdk-qttools-tools \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'nativesdk-qtwayland-dev nativesdk-qtwayland-tools', '', d)} \
"

RDEPENDS_${PN}_remove_mingw32 = " \
    nativesdk-cmake \
    nativesdk-ninja \
    nativesdk-perl-modules \
"
