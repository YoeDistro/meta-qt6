SUMMARY = "The sip module support for PyQt6"
HOMEPAGE = "https://pypi.org/project/PyQt6-sip/"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9cd437778ebd1c056a76b4ded73b3a6d"

SRC_URI[sha256sum] = "2486e1588071943d4f6657ba09096dc9fffd2322ad2c30041e78ea3f037b5778"

inherit pypi setuptools3

PYPI_PACKAGE = "PyQt6_sip"

BBCLASSEXTEND = "native nativesdk"
