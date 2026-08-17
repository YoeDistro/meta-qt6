# meta-mingw appends "--without-cng" for mingw32. libarchive 3.8.8 removed the
# WinCrypt fallback, so its Windows code needs bcrypt unconditionally now and
# the flag only breaks the build.
EXTRA_OECONF:remove:mingw32 = "--without-cng"
