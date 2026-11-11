# libuv 1.52.1 made uv_cpu_info_s.model const in include/uv.h without
# updating src/win/util.c, which takes the address of that field. Since GCC 14
# treats -Wincompatible-pointer-types as an error by default, nativesdk-libuv
# fails do_compile for the mingw32 SDK. Fixed upstream after 1.52.1.
#
# Only the Windows backend is affected, so keep this scoped to mingw32 and
# leave target and native libuv untouched. Applied only for 1.52.1
FILESEXTRAPATHS:prepend := "${THISDIR}/libuv:"

SRC_URI:append:mingw32 = "${@' file://0001-win-fix-const-correctness-compiler-error.patch' if d.getVar('PV') == '1.52.1' else ''}"
