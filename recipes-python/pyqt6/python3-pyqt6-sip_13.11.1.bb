SUMMARY = "The sip module support for PyQt6"
HOMEPAGE = "https://pypi.org/project/PyQt6-sip/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bc996f4e03c98eae60de43496026f863"

SRC_URI[sha256sum] = "869c5b48afe38e55b1ee0dd72182b0886e968cc509b98023ff50010b013ce1be"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "pyqt6_sip"

BBCLASSEXTEND = "native nativesdk"
