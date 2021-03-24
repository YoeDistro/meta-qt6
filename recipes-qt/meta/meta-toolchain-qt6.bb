SUMMARY = "Meta package for building an installable Qt5 toolchain and SDK"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit populate_sdk populate_sdk_qt6_base

TOOLCHAIN_HOST_TASK_append = " nativesdk-packagegroup-qt6-toolchain-host"
TOOLCHAIN_TARGET_TASK_append = " packagegroup-qt6-modules"
