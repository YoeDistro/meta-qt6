LICENSE = "(The-Qt-Company-Commercial | (GPL-3.0-only & Qt-GPL-exception-1.0) & (LGPL-3.0-only | GPL-2.0-only | GPL-3.0-only) & GFDL-1.3-no-invariants-only) & Apache-2.0 & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM = " \
    file://LICENSES/Apache-2.0.txt;md5=b4c615f64dff32f71eeed614d13dfd4c \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/BSL-1.0.txt;md5=8c92b4c255bdcce2989707d5b8a4d302 \
    file://LICENSES/GFDL-1.3-no-invariants-only.txt;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://LICENSES/GPL-2.0-only.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LGPL-3.0-only.txt;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/MIT.txt;md5=3605d54ecceddcd50962eb89318779ec \
    file://LICENSES/Qt-GPL-exception-1.0.txt;md5=9a13522cd91a88fba784baf16ea66af8 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

DEPENDS += "qtbase qtpositioning"

PACKAGECONFIG ?= "osm qml"
PACKAGECONFIG[esri] = "-DFEATURE_geoservices_esri=ON,-DFEATURE_geoservices_esri=OFF,"
PACKAGECONFIG[mapbox] = "-DFEATURE_geoservices_mapbox=ON,-DFEATURE_geoservices_mapbox=OFF,"
PACKAGECONFIG[nokia] = "-DFEATURE_geoservices_nokia=ON,-DFEATURE_geoservices_nokia=OFF,"
PACKAGECONFIG[osm] = "-DFEATURE_geoservices_osm=ON,-DFEATURE_geoservices_osm=OFF,"
PACKAGECONFIG[qml] = ",,qtdeclarative qtdeclarative-native"
