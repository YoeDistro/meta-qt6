SUMMARY = "The PEP 517 compliant PyQt build system"
HOMEPAGE = "https://pypi.org/project/PyQt-builder/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed1d69a33480ebf4ff8a7a760826d84e"

SRC_URI[sha256sum] = "ce9930aafc1ce0af928a6944bcc80ecf78c23ffdcad6ac111306c4c71057848e"

inherit pypi python_setuptools_build_meta native

PYPI_PACKAGE = "pyqt_builder"

DEPENDS += "python3-setuptools-scm-native"
