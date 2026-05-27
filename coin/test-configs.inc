DISTRO = "poky"
# use systemd
INIT_MANAGER:forcevariable = "systemd"

INHERIT += "buildstats buildstats-summary"
INHERIT += "rm_work"

# default to commercial Qt
QT_EDITION = "commercial"

LICENSE_FLAGS_ACCEPTED = "commercial_ffmpeg"

# include commercial addons and enable Qt QML Compiler
QT_COMMERCIAL_MODULES = "1"

# fast compression of SDK tar package
SDK_XZ_COMPRESSION_LEVEL = "-1"

# be more strict with QA warnings, turn them all to errors:
EXTRA_QA = "\
    ldflags useless-rpaths rpaths staticdev libdir xorg-driver-abi \
    textrel already-stripped incompatible-license files-invalid \
    installed-vs-shipped compile-host-path install-host-path \
    pn-overrides infodir build-deps \
    unknown-configure-option symlink-to-sysroot multilib \
    invalid-packageconfig host-user-contaminated uppercase-pn \
"
ERROR_QA:append = "${EXTRA_QA}"
WARN_QA:remove = "${EXTRA_QA}"

# workaround for qemu
# nativesdk-qemu-user-mips rdepends on nativesdk-bash, but it isn't a build dependency
INSANE_SKIP:nativesdk-qemu-user-mips = "build-deps"

# workaround for clang
# nativesdk-liblldb contains reference to TMPDIR [buildpaths]
INSANE_SKIP:append:pn-nativesdk-clang = " buildpaths"

# enable thumb for broader test coverage (oe-core autobuilder doesn't have thumb enabled)
PREFERRED_ARM_INSTRUCTION_SET              ?= "thumb"
ARM_INSTRUCTION_SET = "${PREFERRED_ARM_INSTRUCTION_SET}"

# enable webengine with commercial/proprietary features
PACKAGECONFIG:append:pn-qtwebengine = " proprietary-codecs"

PACKAGECONFIG:append:pn-qtbase = "\
    tslib libproxy gssapi mtdev cups ctf \
    sql-mysql sql-odbc sql-psql sql-sqlite journald brotli \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'gtk', '', d)} \
"

RDEPENDS:${PN}:append:pn-packagegroup-qt6-modules = " packagegroup-qt6-examples"

QT_ENABLE_SBOM_SPDX_JSON = "${@'1' if bb.utils.vercmp_string_op(d.getVar('DISTRO_VERSION'), '5.1', '>') else '0'}"
QT_ENABLE_SBOM_CYCLONEDX = "${@'1' if bb.utils.vercmp_string_op(d.getVar('DISTRO_VERSION'), '6.1', '>=') else '0'}"

# remove all main features, added back in different builds
TEST_DISTRO_FEATURES = "opengl vulkan wayland x11"
DISTRO_FEATURES:remove = "${TEST_DISTRO_FEATURES}"
