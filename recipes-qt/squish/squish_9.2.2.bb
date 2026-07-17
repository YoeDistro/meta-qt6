HOMEPAGE = "https://www.qt.io/quality-assurance/squish"
LICENSE = "LicenseRef-The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a25acc1c5f693af9921102a049958aa8"

EXCLUDE_FROM_WORLD = "1"

inherit qt6-qmake

# location where Squish release packages can be downloaded
SQUISH_MIRROR ?= ""

python __anonymous() {
    if not (d.getVar('SQUISH_MIRROR')):
        raise bb.parse.SkipRecipe("You need to define SQUISH_MIRROR in the config", d)
}

SRC_URI = "\
    ${SQUISH_MIRROR}/${PV}/squish-${PV}-qt611x-linux64.tar.gz;name=squish;subdir=${BP};striplevel=1 \
    ${SQUISH_MIRROR}/${PV}/squish-${PV}-qt-embedded-src.tar.gz;name=qt-squish-embedded;subdir=${BP};striplevel=1 \
"

SRC_URI[squish.sha256sum] = "2122effecbad6e74f385b3f5de4061a91b699b6d4bc83d613c972d292f6564cd"
SRC_URI[qt-squish-embedded.sha256sum] = "2c915b1aee97c33f8407c1451389ea5a7ad85e26051a4d24301cd95c4f47721a"

DEPENDS += "\
    qtbase \
    qt5compat \
"

PACKAGECONFIG ?= "\
    appman \
    qml \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
"
PACKAGECONFIG[appman] = "--enable-qt-appman,--disable-qt-appman,qtapplicationmanager"
PACKAGECONFIG[qml] = ",,qtdeclarative qtdeclarative-native"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,qtwayland wayland wayland-native"

do_configure() {
    ${S}/configure \
        --disable-all \
        --enable-qmake-config \
        --enable-qt \
        --enable-server \
        --with-qmake=${STAGING_DIR_NATIVE}${QT6_INSTALL_BINDIR}/qmake \
        --with-squishidl=${S}/bin/squishidl \
        ${PACKAGECONFIG_CONFARGS}
}

do_compile() {
    ./build ${@oe.utils.parallel_make_argument(d, '-j%d')}
}

do_install() {
    DESTDIR=${D}/opt/squish
    ./build install DESTDIR=${DESTDIR}

    install -d ${D}${QT6_INSTALL_PLUGINSDIR}
    mv ${DESTDIR}/plugins/* ${D}${QT6_INSTALL_PLUGINSDIR}
    rmdir ${DESTDIR}/plugins

    sed -i -e 's|${RECIPE_SYSROOT}||' ${D}/opt/squish/etc/paths.ini

    if [ -e ${D}/opt/squish/etc/squish-appman-hook.yaml ]; then
        sed -i -e 's|PATH_TO_SQUISH|/opt/squish|' ${D}/opt/squish/etc/squish-appman-hook.yaml
    fi

    install -d ${D}${sysconfdir}/profile.d
    cat > ${D}${sysconfdir}/profile.d/squish.sh <<EOF
export SQUISH_PREFIX=/opt/squish
export PATH="\$PATH:/opt/squish/bin"
EOF
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

INSANE_SKIP:${PN}-src += "buildpaths"
