require python3-pyside6.inc

DEPENDS += "qtbase clang-native python3-shiboken6-native"

OECMAKE_SOURCEPATH = "${S}/sources/shiboken6"

EXTRA_OECMAKE += "-DSHIBOKEN_BUILD_LIBS=ON"

BBCLASSEXTEND = "native nativesdk"
