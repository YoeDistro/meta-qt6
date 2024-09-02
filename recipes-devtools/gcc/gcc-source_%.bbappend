FILESEXTRAPATHS:prepend := "${THISDIR}:"
PATCH_GCC ?= "${@bb.utils.contains_any('DISTRO_CODENAME', 'styhead nanbield', bb.utils.vercmp_string_op(d.getVar('GCCVERSION'), '14', '<'), '0', d)}"
SRC_URI += " \
  ${@'file://tree-optimization.110280.patch' if bb.utils.to_boolean(d.getVar('PATCH_GCC')) else ''} \
  "
