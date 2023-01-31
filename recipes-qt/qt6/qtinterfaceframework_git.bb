DESCRIPTION = "Qt Interface Framework"
LICENSE = "(GFDL-1.3 & BSD-3-Clause & The-Qt-Company-GPL-Exception-1.0 & (LGPL-3.0-only | GPL-2.0-or-later)) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6-lts.inc
include recipes-qt/qt6/qt6.inc

python() {
    if 'meta-python' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('Requires meta-python to be present.')
}

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

DEPENDS += "qtbase qtinterfaceframework-native"

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
PACKAGECONFIG[ifmedia] = "-DFEATURE_ifmedia=ON,-DFEATURE_ifmedia=OFF, qtmultimedia qtdeclarative"
PACKAGECONFIG[ifvehiclefunctions] = "-DFEATURE_ifvehiclefunctions=ON,-DFEATURE_ifvehiclefunctions=OFF, qtdeclarative"

PACKAGECONFIG_REFERENCE_API ?= "${@bb.utils.contains('REFERENCE_API', '1', 'ifvehiclefunctions ifmedia taglib', '', d)}"

# simulation support
PACKAGECONFIG[no-media-simulation] = "-DFEATURE_media_qtro_backend=OFF -DFEATURE_media_qtro_simulation_server=OFF -DFEATURE_media_simulation_backend=OFF -DFEATURE_tuner_simulation_backend=OFF"
PACKAGECONFIG[no-vehiclefuntions-simulation] = "-DFEATURE_vehiclefunctions_qtro_backend=OFF -DFEATURE_vehiclefunctions_qtro_simulation_server=OFF -DFEATURE_vehiclefunctions_simulation_backend=OFF"

PACKAGECONFIG_SIMULATION ?= "${@bb.utils.contains('SIMULATION_SUPPORT', '1', '', 'no-media-simulation no-vehiclefuntions-simulation', d)}"

PACKAGECONFIG:class-native ??= "interfaceframework ifcodegen host-tools-only remoteobjects"
PACKAGECONFIG:class-nativesdk ??= "${PACKAGECONFIG:class-native}"

BBCLASSEXTEND = "native nativesdk"

