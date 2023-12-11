SUMMARY = "The PEP 517 compliant PyQt build system"
HOMEPAGE = "https://pypi.org/project/PyQt-builder/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9cd437778ebd1c056a76b4ded73b3a6d"

SRC_URI[sha256sum] = "5b33e99edcb77d4a63a38605f4457a04cff4e254c771ed529ebc9589906ccdb1"

inherit pypi setuptools3 native

PYPI_PACKAGE = "PyQt-builder"
