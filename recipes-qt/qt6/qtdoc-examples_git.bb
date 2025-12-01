LICENSE = "(The-Qt-Company-Commercial | BSD-3-Clause) & Apache-2.0 & CC-BY-4.0 & CC-BY-SA-4.0 & ISC"
LIC_FILES_CHKSUM = " \
    file://LICENSES/Apache-2.0.txt;md5=b4c615f64dff32f71eeed614d13dfd4c \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/CC-BY-4.0.txt;md5=ed88d31cea57b15030a1f58ceb04e0d5 \
    file://LICENSES/CC-BY-SA-4.0.txt;md5=7130783469368ceb248a4f03e89ea4b8 \
    file://LICENSES/ISC.txt;md5=2494cdbaca137fd93842fe9702e9bc4d \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=caa060942f6b722bc4329d4195584c38 \
"

inherit qt6-cmake

QT_MODULE = "qtdoc"

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-examples.inc

DEPENDS += "\
    qtbase \
    qtcharts \
    qtdeclarative \
    qtdeclarative-native \
    qtdoc \
    qtgraphs \
    qtlocation \
    qtmultimedia \
    qtpositioning \
    qtquick3d \
    qtquick3d-native \
    qtsensors \
    qtshadertools-native \
    qtsvg \
    qttools \
    qttools-native \
    qtwebsockets \
"
DEPENDS:append:aarch64 = " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf', '', d)} qtquick3dphysics"
DEPENDS:append:arm = " qtquick3dphysics"
DEPENDS:append:armv6 = " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf', '', d)}"
DEPENDS:append:armv7a = " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf', '', d)}"
DEPENDS:append:armv7ve = " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf', '', d)}"
DEPENDS:append:x86 = " qtquick3dphysics"
DEPENDS:append:x86-64 = " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtpdf', '', d)} qtquick3dphysics"
DEPENDS:remove:libc-musl = "qtpdf"
