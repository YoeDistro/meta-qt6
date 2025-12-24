python do_verify_qt_version() {
    import os, re

    qt_version = d.getVar('QT_VERSION')

    cmake_conf = os.path.join(d.getVar('S'), '.cmake.conf')
    with open(cmake_conf, 'r', encoding='utf-8') as f:
        data = f.read()

    m = re.search(r'set\(QT_REPO_MODULE_VERSION "([0-9.]+)"\)', data)
    if not m:
        bb.fatal("Could not parse QT_REPO_MODULE_VERSION from %s" % cmake_conf)

    repo_version = m.group(1)

    if qt_version != repo_version:
        bb.fatal("Qt version mismatch: QT_VERSION (%s) does not match the sources (%s)" \
            % (qt_version, repo_version))
}

addtask verify_qt_version after do_patch before do_configure

