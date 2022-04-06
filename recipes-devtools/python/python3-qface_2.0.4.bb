inherit pypi setuptools3

DEPS += " python3-jinja2 \
          python3-click \
          python3-pyyaml \
          python3-pytest \
          python3-six \
          python3-path \
          python3-antlr4-runtime \
          python3-watchdog \
          python3-markupsafe \
          python3-setuptools \
        "
DEPENDS += "${DEPS}"
RDEPENDS:${PN} += "${DEPS}"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eee61e10a40b0e3045ee5965bcd9a8b5"
SRC_URI[sha256sum] = "42b0a0d37ed77cf1a9994f5ed77e3a6138468241df1700667a9ab0b848d6ad57"

BBCLASSEXTEND = "nativesdk native"
