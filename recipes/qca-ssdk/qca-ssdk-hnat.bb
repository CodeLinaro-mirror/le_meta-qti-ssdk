DESCRIPTION = "SSDK Switch Driver"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"

inherit module

FILESPATH =+ "${WORKSPACE}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "file://qcom-opensource/qca-ssdk/"
DEPENDS = "virtual/kernel"

S = "${WORKDIR}/qcom-opensource/qca-ssdk"

PACKAGES += "kernel-module-ssdk"

EXTRA_OEMAKE += "TOOL_PATH='${STAGING_BINDIR_TOOLCHAIN}' \
		SYS_PATH='${STAGING_KERNEL_BUILDDIR}' \
		TOOLPREFIX='arm-oe-linux-gnueabi-' \
		KVER='${KERNEL_VERSION}' \
		ARCH='arm' \
		SWCONFIG_FEATURE='disable'  \
		"
EXTRA_OEMAKE += "HNAT_FEATURE=enable"
#EXTRA_OEMAKE += "RFS_FEATURE=enable"

do_install() {
        install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/${PN}
        install -m 0644 build/bin/qca-ssdk${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/${PN}
}

#KERNEL_MODULE_AUTOLOAD += "qca-ssdk"
#module_autoload_qca-ssdk = "qca-ssdk"
