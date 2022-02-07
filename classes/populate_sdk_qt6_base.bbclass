inherit qt6-paths

SDK_POSTPROCESS_COMMAND:prepend = "create_qt6_sdk_files;"

EXE_EXT = ""
EXE_EXT:sdkmingw32 = ".exe"

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
cmake_minimum_required(VERSION 3.11)
include_guard(GLOBAL)

set(ENV{PKG_CONFIG_SYSROOT_DIR} "${SDKTARGETSYSROOT}")
set(ENV{PKG_CONFIG_PATH} "${SDKTARGETSYSROOT}${libdir}/pkgconfig")

set(CMAKE_SYSTEM_NAME Linux)
set(CMAKE_SYSROOT ${SDKTARGETSYSROOT})

set(CMAKE_FIND_ROOT_PATH ${SDKTARGETSYSROOT})
set(CMAKE_FIND_ROOT_PATH_MODE_PROGRAM NEVER)
set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE ONLY)
set(CMAKE_FIND_ROOT_PATH_MODE_PACKAGE ONLY)

set(CMAKE_SYSTEM_PROCESSOR ${TUNE_PKGARCH})

set(CMAKE_C_COMPILER "${SDKPATHNATIVE}${bindir}/${TARGET_SYS}/${TARGET_PREFIX}gcc${EXE_EXT}")
set(CMAKE_CXX_COMPILER "${SDKPATHNATIVE}${bindir}/${TARGET_SYS}/${TARGET_PREFIX}g++${EXE_EXT}")

set(TARGET_COMPILER_FLAGS "${TARGET_CC_ARCH} --sysroot=${SDKTARGETSYSROOT}")
set(TARGET_COMPILER_FLAGS_RELEASE "${TARGET_CFLAGS}")
set(TARGET_LINKER_FLAGS "${TARGET_LDFLAGS}")

include(CMakeInitializeConfigs)

function(cmake_initialize_per_config_variable _PREFIX _DOCSTRING)
  if (_PREFIX MATCHES "CMAKE_(C|CXX|ASM)_FLAGS")
    set(CMAKE_\${CMAKE_MATCH_1}_FLAGS_INIT "\${TARGET_COMPILER_FLAGS}")

    foreach (config DEBUG RELEASE MINSIZEREL RELWITHDEBINFO)
      if (DEFINED TARGET_COMPILER_FLAGS_\${config})
        set(CMAKE_\${CMAKE_MATCH_1}_FLAGS_\${config}_INIT "\${TARGET_COMPILER_FLAGS_\${config}}")
      endif()
    endforeach()
  endif()

  if (_PREFIX MATCHES "CMAKE_(SHARED|MODULE|EXE)_LINKER_FLAGS")
    foreach (config SHARED MODULE EXE)
      set(CMAKE_\${config}_LINKER_FLAGS_INIT "\${TARGET_LINKER_FLAGS}")
    endforeach()
  endif()

  _cmake_initialize_per_config_variable(\${ARGV})
endfunction()

if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX_INITIALIZED_TO_DEFAULT 1)
endif()
set(CMAKE_INSTALL_PREFIX "${prefix}" CACHE PATH "Install path prefix")
EOF
}

create_qt6_sdk_files:append:sdkmingw32() {
    sed -i -e 's|${SDKPATH}|$ENV{SDKPATH}|g' \
        ${SDK_OUTPUT}${SDKPATHNATIVE}/usr/share/cmake/Qt6Toolchain.cmake
}

# default debug prefix map isn't valid in the SDK
DEBUG_PREFIX_MAP = ""
SECURITY_CFLAGS = ""
