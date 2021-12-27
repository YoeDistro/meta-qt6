# Generate Conan profile
do_install:append:class-target() {
    install -d ${D}${datadir}/conan
    cat > ${D}${datadir}/conan/profile <<EOF
[settings]
os=Linux
arch=${@map_conan_arch(d.getVar('TARGET_ARCH'), d)}
compiler=gcc
compiler.version=$(${CC} -dumpfullversion | cut -d . -f 1,2)
compiler.libcxx=libstdc++11
build_type=${BUILD_TYPE}
[options]
qtbase:cmake_args_qtbase="${@cmake_args_qtbase(d)}"
*:cmake_args_leaf_module=""
[build_requires]
[env]
CONAN_CMAKE_GENERATOR=${OECMAKE_GENERATOR}
EOF
}

def map_conan_arch(a, d):
    import re

    valid_archs = "x86, x86_64, ppc32be, ppc32, ppc64le, ppc64, \
                  armv5el, armv5hf, armv6, armv7, armv7hf, armv7s, armv7k, armv8, armv8_32, armv8.3, \
                  sparc, sparcv9, mips, mips64, avr, s390, s390x, asm.js, wasm, sh4le, \
                  e2k-v2, e2k-v3, e2k-v4, e2k-v5, e2k-v6, e2k-v7, xtensalx6, xtensalx106"

    if   re.match('i.86$', a):                  return 'x86'
    elif re.match('x86.64$', a):                return 'x86_64'
    elif re.match('arm$', a):                   return 'arm'
    elif re.match('aarch64$', a):               return 'armv8'
    elif re.match('mips(isa32)?(el)?$', a):     return 'mips'
    elif re.match('mips(isa)?(64)?(el)?$', a):  return 'mips64'
    elif re.match('p(pc|owerpc)$', a):          return 'ppc32be'
    elif re.match('p(pc|owerpc)le$', a):        return 'ppc32'
    elif re.match('p(pc|owerpc)64$', a):        return 'ppc64'
    elif re.match('p(pc|owerpc)64le$', a):      return 'ppc64le'
    elif re.match('sh4$', a):                   return 'sh4le'
    elif a in valid_archs:                      return a
    else:
        bb.warn("cannot map '%s' to a conan architecture" % a)

def cmake_args_qtbase(d):
    import re
    rm_args = ['-DQT_HOST_PATH[^ ]*']
    args = d.getVar('EXTRA_OECMAKE')
    for arg in rm_args:
        args = re.sub(arg, '', args)
    return args

FILES:${PN}-dev += "${datadir}/conan"
