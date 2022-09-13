inherit systemd

SRC_URI += " \
    file://ifmedia-simulation-server.service \
    file://ifvehiclefunctions-simulation-server.service \
    file://if-services.target \
    file://simulation-server.conf \
"

do_install:append:class-target() {
    install -m 0755 -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/ifmedia-simulation-server.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/ifvehiclefunctions-simulation-server.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/if-services.target ${D}${systemd_unitdir}/system/

    install -m 0755 -d ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/simulation-server.conf ${D}${sysconfdir}/
}

SYSTEMD_PACKAGES = "${PN}-simulation"
SYSTEMD_SERVICE:${PN}-simulation = " \
    ifmedia-simulation-server.service \
    ifvehiclefunctions-simulation-server.service \
    if-services.target \
    "

PACKAGE_BEFORE_PN =+ "${PN}-simulation"
FILES:${PN}-simulation = " \
    ${QT6_INSTALL_BINDIR}/ifmedia-simulation-server \
    ${QT6_INSTALL_BINDIR}/ifvehiclefunctions-simulation-server \
    ${QT6_INSTALL_PLUGINSDIR}/interfaceframework/ \
    ${systemd_unitdir}/system \
    ${sysconfdir}/simulation-server.conf \
    "
RRECOMMENDS:${PN} += "${PN}-simulation"
