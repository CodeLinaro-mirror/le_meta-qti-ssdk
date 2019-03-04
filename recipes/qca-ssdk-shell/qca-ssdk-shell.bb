DESCRIPTION = "SSDK Switch Driver"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/${LICENSE};md5=f3b90e78ea0cffb20bf5cca7947a896d"

FILESPATH =+ "${WORKSPACE}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files/:"
SRC_URI = "file://qcom-opensource/qca-ssdk-shell/"

CFLAGS +="--sysroot=${STAGING_DIR_TARGET}"
CFLAGS +="-mfpu=neon -mfloat-abi=hard"

INSANE_SKIP_${PN} += "already-stripped"

S = "${WORKDIR}/qcom-opensource/qca-ssdk-shell"

do_compile() {
	${MAKE} -C ${S} TOOL_PATH='${STAGING_BINDIR_TOOLCHAIN}' \
		SYS_PATH='${STAGING_KERNEL_BUILDDIR}' \
		TOOLPREFIX='${TARGET_PREFIX}' \
		KVER='${KERNEL_VERSION}' \
		ARCH='arm' \
		CPU='arm'
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 build/bin/ssdk_sh ${D}${bindir}
}
