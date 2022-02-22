LICENSE = "GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase qttools qttools-native"

PACKAGES = "${PN}"
PACKAGES_DYNAMIC = "${PN}-*"
PACKAGESPLITFUNCS:prepend = "split_translation_packages "

python split_translation_packages () {
    do_split_packages(d, d.expand('${QT6_INSTALL_TRANSLATIONSDIR}'),
                      r'^(.*?)(?:_..)+\.qm$', d.expand('${PN}-%s'),
                      'Qt translations for %s', extra_depends='')

    # Add dynamic packages to the rrecommends of the main packages
    pn = d.getVar('PN')
    pkgs = oe.utils.packages_filter_out_system(d)
    d.setVar('RRECOMMENDS:' + pn, ' '.join(pkgs))
}

