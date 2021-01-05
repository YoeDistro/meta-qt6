LICENSE = "GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase qttools qttools-native"

PACKAGES_DYNAMIC = "${PN}-*"

python populate_packages_prepend () {
    do_split_packages(d, d.expand('${QT6_INSTALL_TRANSLATIONSDIR}'),
                      r'^(.*?)(?:_..)+\.qm$', d.expand('${PN}-%s'),
                      'Qt translations for %s', extra_depends='')
}

SRCREV = "0b22317dcf1bb59031251d04c1402a310b1a3386"
