SUMMARY = "The sip module support for PyQt6"
HOMEPAGE = "https://pypi.org/project/PyQt6-sip/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bc996f4e03c98eae60de43496026f863"

SRC_URI[sha256sum] = "d463af37738bda1856c9ef513e5620a37b7a005e9d589c986c3304db4a8a14d3"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "pyqt6_sip"

BBCLASSEXTEND = "native nativesdk"
