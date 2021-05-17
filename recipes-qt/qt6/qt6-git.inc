DESCRIPTION ?= "Qt is a cross-platform application development framework for desktop, embedded and mobile."
HOMEPAGE ?= "https://www.qt.io"

QT_MODULE ?= "${BPN}"
QT_MODULE_BRANCH ?= "6.1"
QT_MODULE_BRANCH_PARAM ?= "branch=${QT_MODULE_BRANCH}"

SRC_URI = "${QT_GIT}/${QT_GIT_PROJECT}/${QT_MODULE}.git;name=${QT_MODULE};${QT_MODULE_BRANCH_PARAM};protocol=${QT_GIT_PROTOCOL}"

CVE_PRODUCT = "qt"

S = "${WORKDIR}/git"

PV = "6.1.1"
