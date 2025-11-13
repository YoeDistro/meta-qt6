PREFERRED_TOOLCHAIN_TARGET = "clang"
DISTRO_FEATURES:append = " ld-is-lld"

INSANE_SKIP:grpc-dbg = "buildpaths"
