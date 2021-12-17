LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.QT-LICENSE-AGREEMENT;md5=f383149f021dd9099dd49628e3916808 \
"

inherit qt6-cmake pkgconfig

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-tests-disable-failing-tests.patch \
    file://0004-Do-not-use-QT_TOOLCHAIN_RELOCATABLE-paths-in-qt.tool.patch \
    file://0001-catch_p_p.h-don-t-use-MINSIGSTKSZ.patch \
"

DEPENDS += "\
    freetype \
    pcre2 \
"
DEPENDS:remove:class-native = "qtbase-native"
RDEPENDS_${PN}:remove:class-native = "libssl-native"

PACKAGECONFIG:class-native ?= "gui widgets png dbus no-opengl openssl"
PACKAGECONFIG:class-nativesdk ?= "${PACKAGECONFIG:class-native}"
PACKAGECONFIG ?= "\
    ${PACKAGECONFIG_DEFAULT} \
    ${PACKAGECONFIG_GRAPHICS} \
    ${PACKAGECONFIG_X11} \
    ${PACKAGECONFIG_KDE} \
    ${PACKAGECONFIG_FONTS} \
    ${PACKAGECONFIG_SYSTEM} \
    ${PACKAGECONFIG_DISTRO} \
"

PACKAGECONFIG_GRAPHICS ?= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', \
        bb.utils.contains('DISTRO_FEATURES', 'x11', 'gl', 'gles2 eglfs', d), 'no-opengl linuxfb', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)} \
"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb xkbcommon glib', '', d)}"
PACKAGECONFIG_KDE ?= "${@bb.utils.contains('DISTRO_FEATURES', 'kde', 'sm cups kms gbm sql-sqlite', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= ""
PACKAGECONFIG_DISTRO ?= ""
PACKAGECONFIG_DEFAULT ?= "accessibility dbus udev gui widgets icu openssl  \
    jpeg png dbus libinput fontconfig harfbuzz \
    ${@bb.utils.contains('SELECTED_OPTIMIZATION', '-Os', 'optimize-size ltcg', '', d)} \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'openembedded-layer', 'zstd', '', d)} \
"

PACKAGECONFIG:remove:mingw32 = "openssl"

# Build type: Debug, Release, MinSizeRel, RelWithDebInfo
BUILD_TYPE ?= "Release"
# OpenSSL linking mode: runtime, linked
OPENSSL_LINKING_MODE ?= "runtime"

# Default platform plugin
QT_QPA_DEFAULT_PLATFORM ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb', \
    bb.utils.contains('PACKAGECONFIG', 'gles2', 'eglfs', 'linuxfb', d), d)}"

PACKAGECONFIG[ltcg] = "-DCMAKE_INTERPROCEDURAL_OPTIMIZATION=ON,-DCMAKE_INTERPROCEDURAL_OPTIMIZATION=OFF"
PACKAGECONFIG[optimize-size] = "-DFEATURE_optimize_size=ON,-DFEATURE_optimize_size=OFF"
PACKAGECONFIG[static] = "-DBUILD_SHARED_LIBS=OFF,-DBUILD_SHARED_LIBS=ON"

PACKAGECONFIG[cups] = "-DFEATURE_cups=ON,-DFEATURE_cups=OFF,cups"
PACKAGECONFIG[dbus] = "-DFEATURE_dbus=ON,-DFEATURE_dbus=OFF,dbus"
PACKAGECONFIG[udev] = "-DFEATURE_libudev=ON,-DFEATURE_libudev=OFF,udev"
PACKAGECONFIG[zstd] = "-DFEATURE_zstd=ON,-DFEATURE_zstd=OFF,zstd"

# corelib
PACKAGECONFIG[glib] = "-DFEATURE_glib=ON,-DFEATURE_glib=OFF,glib-2.0"
PACKAGECONFIG[icu] = "-DFEATURE_icu=ON,-DFEATURE_icu=OFF,icu"
PACKAGECONFIG[journald] = "-DFEATURE_journald=ON,-DFEATURE_journald=OFF,systemd"
PACKAGECONFIG[lttng] = "-DFEATURE_lttng=ON,-DFEATURE_lttng=OFF,lttng-ust"

# gui
PACKAGECONFIG[gui] = "-DFEATURE_gui=ON,-DFEATURE_gui=OFF"
PACKAGECONFIG[accessibility] = "-DFEATURE_accessibility=ON,-DFEATURE_accessibility=OFF"
PACKAGECONFIG[directfb] = "-DFEATURE_directfb=ON,-DFEATURE_directfb=OFF,directfb"
PACKAGECONFIG[fontconfig] = "-DFEATURE_fontconfig=ON,-DFEATURE_fontconfig=OFF,fontconfig"
PACKAGECONFIG[gbm] = "-DFEATURE_gbm=ON,-DFEATURE_gbm=OFF,virtual/libgbm"
PACKAGECONFIG[gl] = "-DFEATURE_opengl_desktop=ON,-DFEATURE_opengl_desktop=OFF,virtual/libgl"
PACKAGECONFIG[gles2] = "-DFEATURE_opengles2=ON,-DFEATURE_opengles2=OFF,virtual/libgles2 virtual/egl"
PACKAGECONFIG[eglfs] = "-DFEATURE_eglfs=ON,-DFEATURE_eglfs=OFF"
PACKAGECONFIG[harfbuzz] = "-DFEATURE_harfbuzz=ON,-DFEATURE_harfbuzz=OFF,harfbuzz"
PACKAGECONFIG[jpeg] = "-DFEATURE_jpeg=ON,-DFEATURE_jpeg=OFF,jpeg"
PACKAGECONFIG[kms] = "-DFEATURE_kms=ON,-DFEATURE_kms=OFF,drm virtual/egl"
PACKAGECONFIG[libinput] = "-DFEATURE_libinput=ON,-DFEATURE_libinput=OFF,libinput"
PACKAGECONFIG[linuxfb] = "-DFEATURE_linuxfb=ON,-DFEATURE_linuxfb=OFF"
PACKAGECONFIG[mtdev] = "-DFEATURE_mtdev=ON,-DFEATURE_mtdev=OFF,mtdev"
PACKAGECONFIG[no-opengl] = "-DINPUT_opengl=no"
PACKAGECONFIG[png] = "-DFEATURE_png=ON,-DFEATURE_png=OFF,libpng"
PACKAGECONFIG[tslib] = "-DFEATURE_tslib=ON,-DFEATURE_tslib=OFF,tslib"
PACKAGECONFIG[vulkan] = "-DFEATURE_vulkan=ON,-DFEATURE_vulkan=OFF,vulkan-headers vulkan-loader"
PACKAGECONFIG[xcb] = "-DFEATURE_xcb=ON,-DFEATURE_xcb=OFF,libxcb xcb-util-wm xcb-util-image xcb-util-keysyms xcb-util-renderutil"
PACKAGECONFIG[xkbcommon] = "-DFEATURE_xkbcommon=ON,-DFEATURE_xkbcommon=OFF,libxkbcommon,xkeyboard-config"

# widgets
PACKAGECONFIG[widgets] = "-DFEATURE_widgets=ON,-DFEATURE_widgets=OFF"
PACKAGECONFIG[gtk] = "-DFEATURE_gtk3=ON,-DFEATURE_gtk3=OFF,gtk+3 at-spi2-core"

# network
PACKAGECONFIG[brotli] = "-DFEATURE_brotli=ON,-DFEATURE_brotli=OFF,brotli"
PACKAGECONFIG[gssapi] = "-DFEATURE_gssapi=ON,-DFEATURE_gssapi=OFF,krb5"
PACKAGECONFIG[libproxy] = "-DFEATURE_libproxy=ON,-DFEATURE_libproxy=OFF,libproxy"
PACKAGECONFIG[openssl] = "-DFEATURE_openssl_${OPENSSL_LINKING_MODE}=ON,-DFEATURE_openssl=OFF,openssl,libssl"

# sqldrivers
PACKAGECONFIG[sql-mysql] = "-DFEATURE_sql_mysql=ON,-DFEATURE_sql_mysql=OFF,mysql5"
PACKAGECONFIG[sql-odbc] = "-DFEATURE_sql_odbc=ON,-DFEATURE_sql_odbc=OFF,unixodbc"
PACKAGECONFIG[sql-psql] = "-DFEATURE_sql_psql=ON,-DFEATURE_sql_psql=OFF,postgresql"
PACKAGECONFIG[sql-sqlite] = "-DFEATURE_system_sqlite=ON,-DFEATURE_sql_sqlite=OFF,sqlite3"


EXTRA_OECMAKE += "\
    -DCMAKE_BUILD_TYPE=${BUILD_TYPE} \
    -DQT_EDITION=${QT_EDITION} \
"

EXTRA_OECMAKE:append:class-target = "\
    -DFEATURE_rpath=OFF \
    -DQT_QPA_DEFAULT_PLATFORM=${QT_QPA_DEFAULT_PLATFORM} \
    -DQT_AVOID_CMAKE_ARCHIVING_API=ON \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', '-DFEATURE_use_gold_linker=ON', '-DFEATURE_use_bfd_linker=ON', d)} \
"

SYSROOT_DIRS += "${QT6_INSTALL_MKSPECSDIR}"

do_install:append() {
    sed -i ${D}${libdir}/cmake/Qt6BuildInternals/QtBuildInternalsExtra.cmake \
        -e '/QT_SOURCE_TREE/,+2d'

    # remove mac and android specific scripts that depend on perl and bash
    # to avoid file-rdeps QA Issue.
    rm -f ${D}${QT6_INSTALL_LIBEXECDIR}/android_emulator_launcher.sh
    rm -f ${D}${QT6_INSTALL_MKSPECSDIR}/features/uikit/devices.py
    rm -f ${D}${QT6_INSTALL_MKSPECSDIR}/features/uikit/device_destinations.sh
    rm -f ${D}${QT6_INSTALL_MKSPECSDIR}/features/data/mac/objc_namespace.sh
}

do_install:append:class-nativesdk() {
    install -d ${D}${datadir}/cmake/OEToolchainConfig.cmake.d
    cat > ${D}${datadir}/cmake/OEToolchainConfig.cmake.d/OEQt6Toolchain.cmake <<EOF
set(QT_HOST_PATH "\$ENV{OECORE_NATIVE_SYSROOT}/usr" CACHE PATH "")
set(QT_BUILD_TOOLS_WHEN_CROSSCOMPILING "TRUE" CACHE BOOL "")
EOF

    sed -i ${D}${QT6_INSTALL_BINDIR}/* ${D}${QT6_INSTALL_LIBDIR}/cmake/Qt6/qt.toolchain.cmake \
        -e 's|${RECIPE_SYSROOT_NATIVE}|${SDKPATHNATIVE}|'
}

INSANE_SKIP:${PN}-ptest += "arch"
INHIBIT_PACKAGE_STRIP_FILES = "\
    ${PKGD}${PTEST_PATH}/tests/auto/corelib/plugin/qpluginloader/elftest/corrupt2.elf64.so \
    ${PKGD}${PTEST_PATH}/tests/auto/corelib/plugin/qpluginloader/elftest/corrupt3.elf64.so \
    ${PKGD}${PTEST_PATH}/tests/auto/corelib/plugin/qpluginloader/elftest/debugobj.so \
"

BBCLASSEXTEND = "native nativesdk"

