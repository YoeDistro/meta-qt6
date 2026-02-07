SUMMARY = "The PEP 517 compliant PyQt build system"
HOMEPAGE = "https://pypi.org/project/PyQt-builder/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=236276327275fdb261636fb40b18d88d"

SRC_URI[sha256sum] = "6af6646ba29668751b039bfdced51642cb510e300796b58a4d68b7f956a024d8"

inherit pypi python_setuptools_build_meta native

PYPI_PACKAGE = "pyqt_builder"

DEPENDS += "python3-setuptools-scm-native"
