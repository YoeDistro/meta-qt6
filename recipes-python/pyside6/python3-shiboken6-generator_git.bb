require python3-pyside6.inc

DEPENDS += "qtbase clang"

SRC_URI += "file://0001-shiboken6_generator-Do-not-embed-the-cross-compiler-.patch"

OECMAKE_SOURCEPATH = "${S}/sources/shiboken6_generator"

EXTRA_OECMAKE += "-DPython_SOABI='cpython-${@ d.getVar('PYTHON_BASEVERSION').replace('.', '')}'"

# Only needs libraries from the default search path on target
EXTRA_OECMAKE:append:class-target = " -DCMAKE_SKIP_INSTALL_RPATH=ON"

do_install:append:class-target() {
    sed -i -e 's|${RECIPE_SYSROOT}||g' \
        ${D}${libdir}/cmake/Shiboken6Tools/Shiboken6ToolsTargets.cmake
}

BBCLASSEXTEND = "native nativesdk"

OEQA_BUILDPATHS_SKIP = "/home/qt"
