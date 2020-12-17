LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

inherit qt6-cmake

include recipes-qt/qt6/qt6-git.inc
include recipes-qt/qt6/qt6.inc

SRC_URI +="\
    file://0001-Allow-qtwaylandscanner-to-be-built-without-dependenc.patch \
"

PACKAGECONFIG ?= "\
    wayland-client \
    wayland-server \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcomposite-glx', '', d)} \
"
PACKAGECONFIG_class-native ?= ""
PACKAGECONFIG_class-nativesdk ?= ""

PACKAGECONFIG[wayland-client] = "-DFEATURE_wayland_client=ON,-DFEATURE_wayland_client=OFF,"
PACKAGECONFIG[wayland-server] = "-DFEATURE_wayland_server=ON,-DFEATURE_wayland_server=OFF,"

PACKAGECONFIG[xcomposite-egl] = "-DFEATURE_xcomposite_egl=ON,-DFEATURE_xcomposite_egl=OFF,libxcomposite"
PACKAGECONFIG[xcomposite-glx] = "-DFEATURE_xcomposite_glx=ON,-DFEATURE_xcomposite_glx=OFF,libxcomposite virtual/mesa"

PACKAGECONFIG[dmabuf-client-buffer] = "-DFEATURE_wayland_dmabuf_client_buffer=ON,-DFEATURE_wayland_dmabuf_client_buffer=OFF,libdrm"
PACKAGECONFIG[dmabuf-server-buffer] = "-DFEATURE_wayland_dmabuf_server_buffer=ON,-DFEATURE_wayland_dmabuf_server_buffer=OFF,libdrm"

DEPENDS += "qtbase qtdeclarative qtwayland-native wayland wayland-native"

BBCLASSEXTEND =+ "native nativesdk"

SRCREV = "c83d33069fc49ab188848154eb409b2f4e888bf9"
