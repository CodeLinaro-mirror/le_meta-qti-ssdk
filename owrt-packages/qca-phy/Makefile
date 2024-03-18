include $(TOPDIR)/rules.mk
include $(INCLUDE_DIR)/kernel.mk

PKG_NAME:=qca-phy
PKG_VERSION:=1.0
PKG_RELEASE:=1
PKG_FIXUP:=autoreconf
PKG_LICENSE:=GPL-2.0+

LOCAL_SRC := $(TOPDIR)/src/qcom-opensource/$(PKG_NAME)

include $(INCLUDE_DIR)/package.mk

define KernelPackage/qca-phy
  SECTION:=kernel
  CATEGORY:=Kernel modules
  SUBMENU:=Network Devices
  TITLE:=Kernel driver for QCA PHY
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
	M="$(PKG_BUILD_DIR)" \
	modules
endef

define Build/InstallDev
	$(INSTALL_DIR) $(1)/usr/lib
endef

define KernelPackage/qca-phy/install
	$(INSTALL_DIR) $(1)/lib/modules/
	$(SIGN_KEY) $(PKG_BUILD_DIR)/qca-phy.ko
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/qca-phy.ko $(1)/lib/modules/qca_phy.ko
	$(INSTALL_DIR) $(1)/etc/init.d
	$(INSTALL_BIN) ./files/qti_ethphy.init $(1)/etc/init.d/
	$(INSTALL_DIR) $(TOPDIR)/bin/targets/$(TARGET_VARIANT)/modules
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/qca-phy.ko $(TOPDIR)/bin/targets/$(TARGET_VARIANT)/modules/qca_phy.ko
endef

$(eval $(call KernelPackage,$(PKG_NAME)))
