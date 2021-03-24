PREFERRED_PROVIDER_udev = "systemd"
# use gold
DISTRO_FEATURES_append = " ld-is-gold"
# use systemd
DISTRO_FEATURES_append = " systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = ""
# use wayland
DISTRO_FEATURES_append = " wayland"

INHERIT += "buildstats buildstats-summary"
INHERIT += "rm_work"

# fast compression of SDK tar package
SDK_XZ_COMPRESSION_LEVEL = "-1"

SDK_ARCHIVE_TYPE_sdkmingw32 = "zip"

# be more strict with QA warnings, turn them all to errors:
EXTRA_QA = "\
    ldflags useless-rpaths rpaths staticdev libdir xorg-driver-abi \
    textrel already-stripped incompatible-license files-invalid \
    installed-vs-shipped compile-host-path install-host-path \
    pn-overrides infodir build-deps \
    unknown-configure-option symlink-to-sysroot multilib \
    invalid-packageconfig host-user-contaminated uppercase-pn \
"
ERROR_QA_append = "${EXTRA_QA}"
WARN_QA_remove = "${EXTRA_QA}"

# enable thumb for broader test coverage (oe-core autobuilder doesn't have thumb enabled)
PREFERRED_ARM_INSTRUCTION_SET              ?= "thumb"
ARM_INSTRUCTION_SET = "${PREFERRED_ARM_INSTRUCTION_SET}"

PACKAGECONFIG_append_pn-qtbase = "\
    tslib kms gbm zstd libproxy gssapi vulkan mtdev cups \
    sql-mysql sql-odbc sql-psql sql-sqlite journald brotli \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'gtk', '', d)} \
"

# imageformats mng FIXME
PACKAGECONFIG_append_pn-qtimageformats = " jasper"

# Use latest uninative package
UNINATIVE_MAXGLIBCVERSION = "2.32"

UNINATIVE_URL ?= "http://downloads.yoctoproject.org/releases/uninative/2.9/"
UNINATIVE_CHECKSUM[aarch64] ?= "9f25a667aee225b1dd65c4aea73e01983e825b1cb9b56937932a1ee328b45f81"
UNINATIVE_CHECKSUM[i686] ?= "cae5d73245d95b07cf133b780ba3f6c8d0adca3ffc4e7e7fab999961d5e24d36"
UNINATIVE_CHECKSUM[x86_64] ?= "d07916b95c419c81541a19c8ef0ed8cbd78ae18437ff28a4c8a60ef40518e423"

# enable examples for all Qt modules
PACKAGECONFIG_append_pn-qt3d = " examples"
PACKAGECONFIG_append_pn-qt5compat = " examples"
PACKAGECONFIG_append_pn-qtbase = " examples"
PACKAGECONFIG_append_pn-qtcharts = " examples"
PACKAGECONFIG_append_pn-qtcoap = " examples"
PACKAGECONFIG_append_pn-qtconnectivity = " examples"
PACKAGECONFIG_append_pn-qtdatavis3d = " examples"
PACKAGECONFIG_append_pn-qtdeclarative = " examples"
PACKAGECONFIG_append_pn-qtgraphicaleffects = " examples"
PACKAGECONFIG_append_pn-qtimageformats = " examples"
PACKAGECONFIG_append_pn-qtmqtt = " examples"
PACKAGECONFIG_append_pn-qtnetworkauth = " examples"
PACKAGECONFIG_append_pn-qtopcua = " examples"
PACKAGECONFIG_append_pn-qtpositioning = " examples"
PACKAGECONFIG_append_pn-qtquick3d = " examples"
PACKAGECONFIG_append_pn-qtquickcontrols2 = " examples"
PACKAGECONFIG_append_pn-qtquickdesigner-components = " examples"
PACKAGECONFIG_append_pn-qtquicktimeline = " examples"
PACKAGECONFIG_append_pn-qtremoteobjects = " examples"
PACKAGECONFIG_append_pn-qtscxml = " examples"
PACKAGECONFIG_append_pn-qtsensors = " examples"
PACKAGECONFIG_append_pn-qtserialbus = " examples"
PACKAGECONFIG_append_pn-qtserialport = " examples"
PACKAGECONFIG_append_pn-qtshadertools = " examples"
PACKAGECONFIG_append_pn-qtspeech = " examples"
PACKAGECONFIG_append_pn-qtsvg = " examples"
PACKAGECONFIG_append_pn-qttools = " examples"
PACKAGECONFIG_append_pn-qttranslations = " examples"
PACKAGECONFIG_append_pn-qtvirtualkeyboard = " examples"
PACKAGECONFIG_append_pn-qtwayland = " examples"
PACKAGECONFIG_append_pn-qtwebchannel = " examples"
PACKAGECONFIG_append_pn-qtwebsockets = " examples"
