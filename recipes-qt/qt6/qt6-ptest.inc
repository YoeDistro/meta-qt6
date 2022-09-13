FILESEXTRAPATHS:append := ":${THISDIR}/ptest"
SRC_URI += "file://run-ptest"

inherit ptest

QT_PTEST_ENABLED ?= "${@bb.utils.contains('DISTRO_FEATURES', 'ptest', '1', '0', d)}"
PTEST_ENABLED = "${QT_PTEST_ENABLED}"

DEBUG_PREFIX_MAP += "\
 -fmacro-prefix-map=${D}= \
"

fakeroot do_install_ptest() {
    cat >${WORKDIR}/toolchain-ptest.cmake <<EOF
include(${WORKDIR}/toolchain.cmake)
list(PREPEND CMAKE_FIND_ROOT_PATH ${D})
EOF

    if [ -n "${OE_QMAKE_QMAKE}" ]; then
        # qmake project
        B_PTEST=${B}
        mkdir -p ${B_PTEST}/tests
        cd ${B_PTEST}/tests
        ${OE_QMAKE_QMAKE} -o Makefile ${S}/tests
        oe_runmake
    else
        # cmake project
        B_PTEST=${WORKDIR}/build-ptest
        mkdir -p ${B_PTEST}
        cd ${B_PTEST}
        cmake \
            ${OECMAKE_GENERATOR_ARGS} \
            -DCMAKE_TOOLCHAIN_FILE=${WORKDIR}/toolchain-ptest.cmake \
            -DPython3_EXECUTABLE=${PYTHON} \
            ${EXTRA_OECMAKE} \
            -DQT_BUILD_STANDALONE_TESTS=ON \
            -DQT_ADDITIONAL_PACKAGES_PREFIX_PATH=${D}${QT6_INSTALL_LIBDIR}/cmake \
            -DCMAKE_STAGING_PREFIX=${D}${prefix} \
            -DCMAKE_SKIP_RPATH=ON \
            -DQT_DISABLE_NO_DEFAULT_PATH_IN_QT_PACKAGES=ON \
            ${S} \
            -Wno-dev
        ${CMAKE_VERBOSE} cmake --build ${B_PTEST} --target all
    fi

    # if any auto tests were build
    if [ -e "${B_PTEST}/tests/auto" ]; then
        install -d ${D}${PTEST_PATH}/tests
        cp -r ${B_PTEST}/tests/auto ${D}${PTEST_PATH}/tests

        # remove build files
        find ${D}${PTEST_PATH}/tests \
            \( -wholename "*/.*" -o -wholename "*autogen*" -o -iwholename "*cmake*" -o -name Makefile \) \
            -delete

        for f in $(find ${D}${PTEST_PATH} -name tst_* -executable); do
            test=${f##${D}${PTEST_PATH}/}
            testdir=$(dirname ${test})
            # tests may depend on files from sources
            if [ -e ${S}/${testdir} ]; then
                cp -r ${S}/${testdir}/* ${D}${PTEST_PATH}/${testdir}
            fi
            echo ${test} >> ${D}${PTEST_PATH}/tst_list
        done
    fi
}

INSANE_SKIP:${PN}-ptest += "file-rdeps"

PACKAGESPLITFUNCS =+ "remove_ptest_debug"
remove_ptest_debug() {
    if [ -e ${PKGD}${QT6_INSTALL_LIBDIR}/${BPN}/ptest/tests/auto ]; then
        find ${PKGD}${QT6_INSTALL_LIBDIR}/${BPN}/ptest/tests/auto -depth -type d -name .debug -exec rm -rf '{}' \;
    fi
}
