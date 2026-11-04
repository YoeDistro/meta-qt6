require python3-pyside6.inc

DEPENDS += "qtbase clang"

OECMAKE_SOURCEPATH = "${S}/sources/shiboken6_generator"

EXTRA_OECMAKE += "-DPython_SOABI='cpython-${@ d.getVar('PYTHON_BASEVERSION').replace('.', '')}'"

BBCLASSEXTEND = "native nativesdk"

OEQA_BUILDPATHS_SKIP = "/home/qt"
