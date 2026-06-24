require python3-pyside6.inc

DEPENDS += "qtbase clang"

OECMAKE_SOURCEPATH = "${S}/sources/shiboken6_generator"

EXTRA_OECMAKE += "-DPython_SOABI='cpython-${@ d.getVar('PYTHON_BASEVERSION').replace('.', '')}'"

do_install:append:class-target() {
    # Upstream CMake appends the libclang library directory (which resolves
    # into the recipe-sysroot) to the shiboken6 binary's install RPATH. That
    # leaves a "bad RPATH ...recipe-sysroot/usr/lib" which fails the rpaths QA
    # check. On target libclang lives in the standard ${libdir}, so rewrite the
    # sysroot prefix away.
    if [ -e ${D}${bindir}/shiboken6 ]; then
        chrpath -r '$ORIGIN/:${libdir}' ${D}${bindir}/shiboken6 || true
    fi
    # The exported CMake targets file records the build-time libclang and Qt
    # library directories under the recipe-sysroot, tripping the buildpaths QA
    # check. Strip the sysroot prefix so the on-target paths are referenced.
    cmakefile=${D}${libdir}/cmake/Shiboken6Tools/Shiboken6ToolsTargets.cmake
    if [ -e ${cmakefile} ]; then
        sed -i -e 's|${RECIPE_SYSROOT}||g' ${cmakefile}
    fi
}

BBCLASSEXTEND = "native nativesdk"

OEQA_BUILDPATHS_SKIP = "/home/qt"
