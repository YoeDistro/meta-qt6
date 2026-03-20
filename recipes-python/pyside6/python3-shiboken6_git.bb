require python3-pyside6.inc

DEPENDS += "\
    qtbase \
    python3-shiboken6-generator-native \
"

OECMAKE_SOURCEPATH = "${S}/sources/shiboken6"

EXTRA_OECMAKE += "-DPython_SOABI='cpython-${@ d.getVar('PYTHON_BASEVERSION').replace('.', '')}'"

do_install:append() {
    # shiboken6.pc in package python3-shiboken6-dev contains reference to TMPDIR [buildpaths]
    sed -i ${D}${QT6_INSTALL_LIBDIR}/pkgconfig/shiboken6.pc \
        -e '/^python_/d' \
        -e 's|${RECIPE_SYSROOT}||'
}

FILES:${PN}-dev += "${prefix}/shiboken6/include"

SYSROOT_DIRS += "${prefix}/shiboken6/include"
