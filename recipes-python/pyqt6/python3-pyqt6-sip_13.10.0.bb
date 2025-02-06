SUMMARY = "The sip module support for PyQt6"
HOMEPAGE = "https://github.com/Python-SIP/sip"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bc996f4e03c98eae60de43496026f863"

SRC_URI[sha256sum] = "d6daa95a0bd315d9ec523b549e0ce97455f61ded65d5eafecd83ed2aa4ae5350"

inherit pypi setuptools3

PYPI_PACKAGE = "pyqt6_sip"

BBCLASSEXTEND = "native nativesdk"
