DESCRIPTION = "Qt component for application lifecycle management"
LICENSE = "BSD-3-Clause AND GFDL-1.3-no-invariants-only AND GPL-3.0-only WITH Qt-GPL-exception-1.0 OR LicenseRef-The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/GFDL-1.3-no-invariants-only.txt;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/Qt-GPL-exception-1.0.txt;md5=9a13522cd91a88fba784baf16ea66af8 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase qtdeclarative qtapplicationmanager-native"
DEPENDS:append:libc-musl = " libexecinfo"
RDEPENDS:${PN}:class-target = "libcrypto ${PN}-tools"

EXTRA_OECMAKE += "\
    -DFEATURE_am_reproducible_build=ON \
    ${@'-DINPUT_hardware_id=' + d.getVar('QT_APPLICATIONMANAGER_hardware_id') \
                             if d.getVar('QT_APPLICATIONMANAGER_hardware_id') else ''} \
"

PACKAGECONFIG ?= "installer system-libyaml system-libarchive \
                  ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'multi-process bubblewrap', '', d)}"

PACKAGECONFIG[tools-only] = "-DFEATURE_am_tools_only=ON, -DFEATURE_am_tools_only=OFF"
PACKAGECONFIG[multi-process] = "-DFEATURE_am_multi_process=ON,-DFEATURE_am_multi_process=OFF,qtwayland"
PACKAGECONFIG[bubblewrap] = "-DFEATURE_am_bubblewrap_container=ON,-DFEATURE_am_bubblewrap_container=OFF,,bubblewrap"
PACKAGECONFIG[installer] = "-DFEATURE_am_installer=ON,-DFEATURE_am_installer=OFF"
PACKAGECONFIG[package-server] = "-DFEATURE_am_package_server=ON,-DFEATURE_am_package_server=OFF,qthttpserver"
PACKAGECONFIG[widgets-support] = "-DFEATURE_am_widgets_support=ON,-DFEATURE_am_widgets_support=OFF"
PACKAGECONFIG[dltlogging] = "-DFEATURE_am_dltlogging=ON,-DFEATURE_am_dltlogging=OFF,qtdltlogging"
PACKAGECONFIG[libbacktrace] = "-DFEATURE_am_libbacktrace=ON,-DFEATURE_am_libbacktrace=OFF,libbacktrace"
PACKAGECONFIG[system-libyaml] = "-DINPUT_libyaml=system,-DINPUT_libyaml=qt,libyaml"
PACKAGECONFIG[system-libarchive] = "-DINPUT_libarchive=system,-DINPUT_libarchive=qt,libarchive"

PACKAGECONFIG:class-native ??= "tools-only installer"
PACKAGECONFIG:class-nativesdk ??= "${PACKAGECONFIG:class-native}"

FILES:${PN}-tools = "\
    ${QT6_INSTALL_BINDIR}/appman-package-server* \
    ${QT6_INSTALL_BINDIR}/appman-packager* \
    ${QT6_INSTALL_BINDIR}/appman-qmltestrunner* \
"

BBCLASSEXTEND = "nativesdk native"

INSANE_SKIP:${PN}-ptest += "buildpaths"
