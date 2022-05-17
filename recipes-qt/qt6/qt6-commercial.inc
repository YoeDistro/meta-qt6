python() {
    if d.getVar('QT_EDITION') != 'commercial':
        raise bb.parse.SkipRecipe('Available only with Commercial Qt')
}

QT_GIT = "git://codereview.qt-project.org"
QT_GIT_PROTOCOL = "ssh"
QT_MODULE = "tqtc-${BPN}"
