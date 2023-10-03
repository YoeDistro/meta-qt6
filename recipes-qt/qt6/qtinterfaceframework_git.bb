DESCRIPTION = "Qt Interface Framework"
LICENSE = "The-Qt-Company-Commercial | (GPL-3.0-only & Qt-GPL-exception-1.0) & GFDL-1.3-no-invariants-only & BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSES/BSD-3-Clause.txt;md5=cb40fa7520502d8c7a3aea47cae1316c \
    file://LICENSES/GFDL-1.3-no-invariants-only.txt;md5=a22d0be1ce2284b67950a4d1673dd1b0 \
    file://LICENSES/GPL-3.0-only.txt;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSES/LicenseRef-Qt-Commercial.txt;md5=40a1036f91cefc0e3fabad241fb5f187 \
    file://LICENSES/Qt-GPL-exception-1.0.txt;md5=9a13522cd91a88fba784baf16ea66af8 \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

# Default build and package these
REFERENCE_API ?= "1"
SIMULATION_SUPPORT ?= "1"
include ${@bb.utils.contains('SIMULATION_SUPPORT', '1', 'qtinterfaceframework.inc', '', d)}

FILES:${PN}-dev += " \
    ${QT6_INSTALL_DATADIR}/ifcodegen-templates \
    "

PACKAGE_BEFORE_PN =+ "${PN}-refapi-media"
FILES:${PN}-refapi-media = "\
    ${QT6_INSTALL_LIBDIR}/libQt6IfMedia.so.* \
    ${QT6_INSTALL_LIBDIR}/qml/QtInterfaceFramework/Media \
    "

PACKAGE_BEFORE_PN =+ "${PN}-refapi-vehiclefuntions"
FILES:${PN}-refapi-vehiclefuntions = "\
    ${QT6_INSTALL_LIBDIR}/libQt6IfVehicleFunctions.so.* \
    ${QT6_INSTALL_LIBDIR}/qml/QtInterfaceFramework/VehicleFunctions \
    "

DEPENDS += "qtbase qtdeclarative qtdeclarative-native qtinterfaceframework-native"

PRIVATE_LIBS:${PN}-examples = "libInstrumentCluster.so"

PACKAGECONFIG ?= "ifcodegen remoteobjects interfaceframework \
    ${PACKAGECONFIG_REFERENCE_API} \
    ${PACKAGECONFIG_SIMULATION} \
    "

PACKAGECONFIG[taglib] = "-DFEATURE_taglib=ON,-DFEATURE_taglib=OFF,taglib"
PACKAGECONFIG[host-tools-only] = "-DFEATURE_host_tools_only=ON,-DFEATURE_host_tools_only=OFF"
PACKAGECONFIG[ifcodegen] = "-DFEATURE_ifcodegen=ON,-DFEATURE_ifcodegen=OFF,python3-qface,python3-qface"
PACKAGECONFIG[remoteobjects] = "-DFEATURE_remoteobjects=ON,-DFEATURE_remoteobjects=OFF,qtremoteobjects qtremoteobjects-native"

#interfaceframework only
PACKAGECONFIG[interfaceframework] = "-DFEATURE_interfaceframework=ON,-DFEATURE_interfaceframework=OFF"

# reference API's
PACKAGECONFIG[ifmedia] = "-DFEATURE_ifmedia=ON,-DFEATURE_ifmedia=OFF,qtmultimedia"
PACKAGECONFIG[ifvehiclefunctions] = "-DFEATURE_ifvehiclefunctions=ON,-DFEATURE_ifvehiclefunctions=OFF"

PACKAGECONFIG_REFERENCE_API ?= "${@bb.utils.contains('REFERENCE_API', '1', 'ifvehiclefunctions ifmedia taglib', '', d)}"

# simulation support
PACKAGECONFIG[no-media-simulation] = "-DFEATURE_media_qtro_backend=OFF -DFEATURE_media_qtro_simulation_server=OFF -DFEATURE_media_simulation_backend=OFF -DFEATURE_tuner_simulation_backend=OFF"
PACKAGECONFIG[no-vehiclefuntions-simulation] = "-DFEATURE_vehiclefunctions_qtro_backend=OFF -DFEATURE_vehiclefunctions_qtro_simulation_server=OFF -DFEATURE_vehiclefunctions_simulation_backend=OFF"

PACKAGECONFIG_SIMULATION ?= "${@bb.utils.contains('SIMULATION_SUPPORT', '1', '', 'no-media-simulation no-vehiclefuntions-simulation', d)}"

PACKAGECONFIG:class-native ??= "interfaceframework ifcodegen host-tools-only remoteobjects"
PACKAGECONFIG:class-nativesdk ??= "${PACKAGECONFIG:class-native}"

BBCLASSEXTEND = "native nativesdk"

