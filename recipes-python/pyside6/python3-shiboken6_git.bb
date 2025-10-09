require python3-pyside6.inc

DEPENDS += "qtbase clang-native python3-shiboken6-native"

OECMAKE_SOURCEPATH = "${S}/sources/shiboken6"

EXTRA_OECMAKE += "-DSHIBOKEN_BUILD_LIBS=ON"

do_install:append() {
    # shiboken6.pc in package python3-shiboken6-dev contains reference to TMPDIR [buildpaths]
    sed -i ${D}${QT6_INSTALL_LIBDIR}/pkgconfig/shiboken6.pc \
        -e '/^python_/d' \
        -e 's|${RECIPE_SYSROOT}||'
}

BBCLASSEXTEND = "native nativesdk"
