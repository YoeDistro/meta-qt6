SUMMARY = "The PEP 517 compliant PyQt build system"
HOMEPAGE = "https://pypi.org/project/PyQt-builder/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9cd437778ebd1c056a76b4ded73b3a6d"

SRC_URI[sha256sum] = "39f8c75db17d9ce17cb6bbf3df1650b5cebc1ea4e5bd73843d21cc96612b2ae1"

inherit pypi setuptools3 native

PYPI_PACKAGE = "PyQt-builder"
