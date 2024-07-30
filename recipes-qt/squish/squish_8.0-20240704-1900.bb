LICENSE = "Squish-Commercial-License-Agreement"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=899603087536444c628655da3a982a75"

EXCLUDE_FROM_WORLD = "1"

inherit qt6-qmake

SQUISH_MIRROR ?= "https://ci-files01-hki.ci.qt.io/input/squish/coin/67x"

SRC_URI = "\
    ${SQUISH_MIRROR}/squish-${PV}-qt67x-linux64.run;name=squish \
    ${SQUISH_MIRROR}/squish-${PV}-qt-embedded-src.tar.gz;name=qt-squish-embedded \
"

SRC_URI[squish.sha256sum] = "5a84e611a9bdda23f5fcc3ee08945f99d13629afa695c6bb38c59bf4ad5c3591"
SRC_URI[qt-squish-embedded.sha256sum] = "64fe9a6728c299fa29ee7c03077edd0562e9a79198511e74dd4d89ca9adddf2a"

S = "${WORKDIR}/squish-${PV}-qt-embedded-src"
B = "${WORKDIR}/build"

DEPENDS += "\
    qtbase \
    qt5compat \
    qtdeclarative qtdeclarative-native \
    qtapplicationmanager \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-native wayland wayland-native', '', d)} \
"

lcl_maybe_fortify = ""
OE_QMAKE_PATH_HOST_LIBEXECS = "${STAGING_DIR_NATIVE}/${QT6_INSTALL_LIBEXECDIR}"

do_install_squish[cleandirs] = "${WORKDIR}/squish"
do_install_squish[network] = "1"
do_install_squish() {
    chmod +x ${WORKDIR}/squish-${PV}-qt67x-linux64.run
    TMPDIR=${WORKDIR}/tmp XDG_RUNTIME_DIR=${WORKDIR}/tmp ${WORKDIR}/squish-${PV}-qt67x-linux64.run \
        -platform minimal unattended=1 targetdir=${WORKDIR}/squish ide=0
}

do_configure() {
    ${S}/configure \
        --disable-all \
        --enable-qmake-config \
        --enable-qt \
        --enable-server \
        --enable-wayland \
        --with-qmake=${STAGING_DIR_NATIVE}${QT6_INSTALL_BINDIR}/qmake \
        --with-squishidl=${WORKDIR}/squish/bin/squishidl
}

do_compile() {
    ./build -j${@oe.utils.cpu_count()}
}

do_install() {
    DESTDIR=${D}/opt/squish
    ./build install DESTDIR=${DESTDIR}

    install -d ${D}${QT6_INSTALL_PLUGINSDIR}
    mv ${DESTDIR}/plugins/* ${D}${QT6_INSTALL_PLUGINSDIR}
    rmdir ${DESTDIR}/plugins

    sed -i -e 's|${RECIPE_SYSROOT}||' ${D}/opt/squish/etc/paths.ini

    install -d ${D}${sysconfdir}/profile.d
    echo "export SQUISH_PREFIX=/opt/squish" > ${D}${sysconfdir}/profile.d/squish.sh
}

FILES:${PN} += "\
    ${QT6_INSTALL_PLUGINSDIR} \
    /opt/squish \
"
FILES:${PN}-dev += "\
    /opt/squish/qtbuiltinhook.pri \
    /opt/squish/LICENSE.txt \
    /opt/squish/include \
    /opt/squish/lib/cmake \
    /opt/squish/sdk \
"
FILES:${PN}-dev:remove = "${FILES_SOLIBSDEV}"
FILES:${PN}-staticdev += "\
    ${QT6_INSTALL_PLUGINSDIR}/generic/*.a \
    /opt/squish/lib/*.a \
    /opt/squish/lib/extensions/qt/*.a \
"

addtask install_squish after do_unpack before do_configure
