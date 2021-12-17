inherit qt6-paths

SDK_POSTPROCESS_COMMAND:prepend = "create_qt6_sdk_files;"


PATH_DELIM = ":"
PATH_DELIM:sdkmingw32 = ";"

QT6_INSTALL_HOST_LIBEXECDIR = "${QT6_INSTALL_LIBEXECDIR}"
QT6_INSTALL_HOST_LIBEXECDIR:sdkmingw32 = "${QT6_INSTALL_LIBEXECDIR:mingw32}"

create_qt6_sdk_files () {
    # Generate a qt.conf file to be deployed with the SDK
    qtconf=${WORKDIR}/qt.conf
    echo '[Paths]' > $qtconf
    echo 'Prefix = ${prefix}' >> $qtconf
    echo 'Headers = ${QT6_INSTALL_INCLUDEDIR}' >> $qtconf
    echo 'Libraries = ${QT6_INSTALL_LIBDIR}' >> $qtconf
    echo 'ArchData = ${QT6_INSTALL_ARCHDATADIR}' >> $qtconf
    echo 'Data = ${QT6_INSTALL_DATADIR}' >> $qtconf
    echo 'Binaries = ${QT6_INSTALL_BINDIR}' >> $qtconf
    echo 'LibraryExecutables = ${QT6_INSTALL_LIBEXECDIR}' >> $qtconf
    echo 'Plugins = ${QT6_INSTALL_PLUGINSDIR}' >> $qtconf
    echo 'Qml2Imports = ${QT6_INSTALL_QMLDIR}' >> $qtconf
    echo 'Translations = ${QT6_INSTALL_TRANSLATIONSDIR}' >> $qtconf
    echo 'Documentation = ${QT6_INSTALL_DOCDIR}' >> $qtconf
    echo 'Settings = ${QT6_INSTALL_SYSCONFDIR}' >> $qtconf
    echo 'Examples = ${QT6_INSTALL_EXAMPLESDIR}' >> $qtconf
    echo 'Tests = ${QT6_INSTALL_TESTSDIR}' >> $qtconf
    echo 'HostPrefix = ${@os.path.relpath(d.expand("${SDKPATHNATIVE}"), d.expand("${SDKPATHNATIVE}${QT6_INSTALL_BINDIR}"))}' >> $qtconf
    echo 'HostData = ${@os.path.relpath(d.expand("${SDKTARGETSYSROOT}${QT6_INSTALL_ARCHDATADIR}"), d.expand("${SDKPATHNATIVE}"))}' >> $qtconf
    echo 'HostBinaries = ${@os.path.relpath(d.expand("${SDKPATHNATIVE}${QT6_INSTALL_BINDIR}"), d.expand("${SDKPATHNATIVE}"))}' >> $qtconf
    echo 'HostLibraries = ${@os.path.relpath(d.expand("${SDKPATHNATIVE}${QT6_INSTALL_LIBDIR}"), d.expand("${SDKPATHNATIVE}"))}' >> $qtconf
    echo 'HostLibraryExecutables = ${@os.path.relpath(d.expand("${SDKPATHNATIVE}${QT6_INSTALL_HOST_LIBEXECDIR}"), d.expand("${SDKPATHNATIVE}"))}' >> $qtconf
    echo 'Sysroot = ${@os.path.relpath(d.expand("${SDKTARGETSYSROOT}"), d.expand("${SDKPATHNATIVE}${QT6_INSTALL_BINDIR}"))}' >> $qtconf
    echo 'HostSpec = linux-oe-g++' >> $qtconf
    echo 'TargetSpec = linux-oe-g++' >> $qtconf
    echo 'SysrootifyPrefix = true' >> $qtconf

    # add qt.conf to both bin and libexec dirs
    cp ${WORKDIR}/qt.conf ${SDK_OUTPUT}${SDKPATHNATIVE}${QT6_INSTALL_BINDIR}/
    cp ${WORKDIR}/qt.conf ${SDK_OUTPUT}${SDKPATHNATIVE}${QT6_INSTALL_HOST_LIBEXECDIR}/
    cp ${WORKDIR}/qt.conf ${SDK_OUTPUT}${SDKPATHNATIVE}${QT6_INSTALL_BINDIR}/target_qt.conf

    install -d ${SDK_OUTPUT}${SDKPATHNATIVE}/environment-setup.d
    script=${SDK_OUTPUT}${SDKPATHNATIVE}/environment-setup.d/qt6.sh
    touch $script
    echo 'export OE_QMAKE_CFLAGS="$CFLAGS"' >> $script
    echo 'export OE_QMAKE_CXXFLAGS="$CXXFLAGS"' >> $script
    echo 'export OE_QMAKE_LDFLAGS="$LDFLAGS"' >> $script
    echo 'export OE_QMAKE_CC="$CC"' >> $script
    echo 'export OE_QMAKE_CXX="$CXX"' >> $script
    echo 'export OE_QMAKE_LINK="$CXX"' >> $script
    echo 'export OE_QMAKE_AR="$AR"' >> $script
    echo 'export OE_QMAKE_STRIP="$STRIP"' >> $script
    echo 'export OE_QMAKE_OBJCOPY="$OBJCOPY"' >> $script
    echo 'export OE_QMAKE_AR_LTCG="${HOST_PREFIX}gcc-ar"' >> $script

    mkspec=${SDK_OUTPUT}${SDKTARGETSYSROOT}${QT6_INSTALL_MKSPECSDIR}/linux-oe-g++/qmake.conf
    echo "count(QMAKE_AR, 1): QMAKE_AR = ${AR} cqs" >> $mkspec
    echo "count(QMAKE_AR_LTCG, 1): QMAKE_AR_LTCG = ${HOST_PREFIX}gcc-ar cqs" >> $mkspec
    echo "isEmpty(QMAKE_STRIP): QMAKE_STRIP = ${STRIP}" >> $mkspec
    echo "isEmpty(QMAKE_OBJCOPY): QMAKE_OBJCOPY = ${OBJCOPY}" >> $mkspec
    echo "isEmpty(QMAKE_CC): QMAKE_CC = ${CC}" >> $mkspec
    echo "isEmpty(QMAKE_CFLAGS): QMAKE_CFLAGS =  ${CFLAGS}" >> $mkspec
    echo "isEmpty(QMAKE_CXX): QMAKE_CXX = ${CXX}" >> $mkspec
    echo "isEmpty(QMAKE_CXXFLAGS): QMAKE_CXXFLAGS =  ${CXXFLAGS}" >> $mkspec
    echo "isEmpty(QMAKE_LINK): QMAKE_LINK = ${CXX}" >> $mkspec
    echo "isEmpty(QMAKE_LFLAGS): QMAKE_LFLAGS = ${LDFLAGS}" >> $mkspec
    sed -i $mkspec \
        -e 's:${RECIPE_SYSROOT}:$$[QT_SYSROOT]:' \
        -e 's:${TARGET_PREFIX}:$$[QT_HOST_PREFIX]${bindir}/${TARGET_SYS}/${TARGET_PREFIX}:'

    # Generate a toolchain file for using Qt without running setup-environment script
    cat > ${SDK_OUTPUT}${SDKPATHNATIVE}/usr/share/cmake/Qt6Toolchain.cmake <<EOF
set(ENV{PATH} "${SDKPATHNATIVE}${bindir}${PATH_DELIM}${SDKPATHNATIVE}${bindir}/${TARGET_SYS}${PATH_DELIM}\$ENV{PATH}")
set(ENV{CC} "${TARGET_PREFIX}gcc ${TARGET_CC_ARCH} --sysroot=${SDKTARGETSYSROOT}")
set(ENV{CXX} "${TARGET_PREFIX}g++ ${TARGET_CC_ARCH} --sysroot=${SDKTARGETSYSROOT}")

set(ENV{CFLAGS} "${TARGET_CFLAGS}")
set(ENV{CXXFLAGS} "${TARGET_CXXFLAGS}")

set(ENV{OECORE_NATIVE_SYSROOT} "${SDKPATHNATIVE}")
set(ENV{OECORE_TARGET_SYSROOT} "${SDKTARGETSYSROOT}")
set(ENV{SDKTARGETSYSROOT} "${SDKTARGETSYSROOT}")

set(ENV{PKG_CONFIG_SYSROOT_DIR} "${SDKTARGETSYSROOT}")
set(ENV{PKG_CONFIG_PATH} "${SDKTARGETSYSROOT}${libdir}/pkgconfig")

if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT 1)
endif()
set(CMAKE_INSTALL_PREFIX "${prefix}" CACHE PATH "Install path prefix")

set(CMAKE_TOOLCHAIN_FILE "${SDKPATHNATIVE}/usr/share/cmake/OEToolchainConfig.cmake")
include("\${CMAKE_TOOLCHAIN_FILE}")
EOF

    # and use that from the default toolchain file qt.toolchain.cmake
    sed -i ${SDK_OUTPUT}${SDKPATHNATIVE}${QT6_INSTALL_LIBDIR}/cmake/Qt6/qt.toolchain.cmake \
        -e 's|/.*/toolchain.cmake|${SDKPATHNATIVE}/usr/share/cmake/Qt6Toolchain.cmake|'
}

create_qt6_sdk_files:append:sdkmingw32() {
    sed -i -e 's|${SDKPATH}|$ENV{SDKPATH}|g' \
        ${SDK_OUTPUT}${SDKPATHNATIVE}/usr/share/cmake/Qt6Toolchain.cmake
}

# default debug prefix map isn't valid in the SDK
DEBUG_PREFIX_MAP = ""
SECURITY_CFLAGS = ""
