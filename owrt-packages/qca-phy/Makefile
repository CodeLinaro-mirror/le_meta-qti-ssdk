include $(TOPDIR)/rules.mk
include $(INCLUDE_DIR)/kernel.mk

PKG_NAME:=qca-phy
PKG_VERSION:=1.0
PKG_RELEASE:=1

ifeq ($(VARIANT),perf)
  COMPILE_MODE :=perf
else
  COMPILE_MODE :=debug
endif

LOCAL_SRC ?= $(TOPDIR)/src/qcom-opensource/$(PKG_NAME)
EXTRA_ADDR :=lib/modules/$(LINUX_VERSION)-${COMPILE_MODE}/extra

include $(INCLUDE_DIR)/package.mk

define KernelPackage/qca-phy
	SECTION:=kernel
	CATEGORY:=Kernel modules
	SUBMENU:=Network Devices
	TITLE:=Kernel driver for QCA PHY
	FILES:=$(PKG_BUILD_DIR)/qca-phy.ko
	AUTOLOAD:=$(call AutoLoad,30,qca-phy)
endef

define KernelPackage/qca-phy/Description
	This package contains qca-phy driver for QCA chipset
endef

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)/
	$(CP) $(LOCAL_SRC)/* $(PKG_BUILD_DIR)/
endef

define Build/Compile
	$(MAKE) -C "$(LINUX_DIR)" \
		   $(KERNEL_MAKEOPTS) \
		   M="$(PKG_BUILD_DIR)" \
		   CC="clang" \
		   modules
endef

define Build/InstallDev
	$(INSTALL_DIR) $(1)/usr/lib
endef

define KernelPackage/qca-phy/install
	$(INSTALL_DIR) $(1)/$(EXTRA_ADDR)/
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/qca-phy.ko $(1)/$(EXTRA_ADDR)/
endef

$(eval $(call KernelPackage,qca-phy))
