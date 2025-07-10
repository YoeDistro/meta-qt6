LICENSE = "Squish-Commercial-License-Agreement"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=a25acc1c5f693af9921102a049958aa8"

EXCLUDE_FROM_WORLD = "1"

inherit qt6-qmake

# location where Squish release packages can be downloaded
SQUISH_MIRROR ?= ""
SQUISH_LICENSE_KEY ?= ""

python __anonymous() {
    if not (d.getVar('SQUISH_MIRROR') and d.getVar('SQUISH_LICENSE_KEY')):
        raise bb.parse.SkipRecipe("You need to define SQUISH_MIRROR and SQUISH_LICENSE_KEY in the config", d)
}

SQUISH_INSTALLER = "squish-${PV}-qt68x-linux64.run"

SRC_URI = "\
    ${SQUISH_MIRROR}/${SQUISH_INSTALLER};name=squish \
    ${SQUISH_MIRROR}/squish-${PV}-qt-embedded-src.tar.gz;name=qt-squish-embedded;subdir=${BP};striplevel=1 \
"

SRC_URI[squish.sha256sum] = "e697cbe7928594138237e827f6bd58eb495367baf857613ddbc674bb6bca5685"
SRC_URI[qt-squish-embedded.sha256sum] = "91201fcd038e48d7fefc68a3ad25dc6814999129679600e130e5451cd1a37d4f"

DEPENDS += "\
    qtbase \
    qt5compat \
"

PACKAGECONFIG ?= "\
    appman \
    qml \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland', d)} \
"
PACKAGECONFIG[appman] = ",,qtapplicationmanager"
PACKAGECONFIG[qml] = ",,qtdeclarative qtdeclarative-native"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,qtwayland qtwayland-native wayland wayland-native"

lcl_maybe_fortify = ""
OE_QMAKE_PATH_HOST_LIBEXECS = "${STAGING_DIR_NATIVE}/${QT6_INSTALL_LIBEXECDIR}"

do_install_squish[cleandirs] = "${WORKDIR}/squish"
do_install_squish[network] = "1"
do_install_squish() {
    SQUISH_INSTALLER=${UNPACKDIR}/${SQUISH_INSTALLER}
    if [ ! -e $SQUISH_INSTALLER ]; then
        SQUISH_INSTALLER=${WORKDIR}/${SQUISH_INSTALLER}
    fi
    chmod +x $SQUISH_INSTALLER
    TMPDIR=${WORKDIR}/tmp XDG_RUNTIME_DIR=${WORKDIR}/tmp $SQUISH_INSTALLER \
        -platform minimal unattended=1 targetdir=${WORKDIR}/squish ide=0 \
        licensekey=${SQUISH_LICENSE_KEY}
}

do_configure() {
    ${S}/configure \
        --disable-all \
        --enable-qmake-config \
        --enable-qt \
        --enable-server \
        --with-qmake=${STAGING_DIR_NATIVE}${QT6_INSTALL_BINDIR}/qmake \
        --with-squishidl=${WORKDIR}/squish/bin/squishidl \
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

addtask install_squish after do_unpack before do_configure

INSANE_SKIP:${PN}-src += "buildpaths"
