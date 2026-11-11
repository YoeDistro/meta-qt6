FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# meta-mingw appends "--without-cng" for mingw32. libarchive 3.8.8 removed the
# WinCrypt fallback, so its Windows code needs bcrypt unconditionally now and
# the flag only breaks the build.
EXTRA_OECONF:remove:mingw32 = "--without-cng"

# libarchive 3.8.10 links XmlLite on Windows with "-lxmllite -luuid -lole32".
# mingw-w64's libuuid.a is a plain static archive, not an import library, so
# libtool refuses to create a DLL and installs only libarchive.a. Consumers such
# as nativesdk-cmake then fail to link (undefined lzma_*, ZSTD_*, BCrypt*,
# CreateXmlReader). Disable XmlLite to get libarchive.dll.a back.
# Applied from 3.8.10 onwards; 3.8.9 unaffected.
EXTRA_OECONF:append:mingw32 = "${@' --without-xmllite' if bb.utils.vercmp_string_op(d.getVar('PV'), '3.8.10', '>=') else ''}"
