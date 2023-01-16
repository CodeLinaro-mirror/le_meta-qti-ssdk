include $(TOPDIR)/rules.mk

PKG_NAME:=macsec-shell
PKG_VERSION:=1.0
PKG_RELEASE:=1


include $(INCLUDE_DIR)/package.mk

LOCAL_SRC:= $(TOPDIR)/src/qcom-opensource/qca-phy/macsec

define Package/$(PKG_NAME)
  SECTION:=QTI
  CATEGORY:=QTI-PACKAGES
  TITLE:=Shell for QTI NSS macsec
  DEPENDS:= +libpthread
endef

define Package/$(PKG_NAME)/Description
	Contains macsec_shell and libfal for QTI NSS macsec
endef

QCAMACSEC_MAKEOPTS+= TOOL_PATH=$(TOOLCHAIN_DIR)/bin/ \
                     SYS_PATH=$(LINUX_DIR) \
                     TOOLPREFIX=$(TARGET_CROSS) \
                     KVER=$(LINUX_VERSION) \
                     ARCH=$(LINUX_KARCH)

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)/
	$(CP) $(LOCAL_SRC)/* $(PKG_BUILD_DIR)/
endef

define Build/Compile
	$(MAKE) -C $(PKG_BUILD_DIR) $(strip $(QCAMACSEC_MAKEOPTS)) -f Makefile.shell
endef

define Build/InstallDev
	$(INSTALL_DIR) $(1)/usr/include/qca-macsec
	$(INSTALL_DIR) $(1)/usr/lib
	$(CP) $(PKG_BUILD_DIR)/include/nss_macsec_types.h $(1)/usr/include/qca-macsec
	$(CP) $(PKG_BUILD_DIR)/include/nss_macsec_secy.h $(1)/usr/include/qca-macsec
	$(CP) $(PKG_BUILD_DIR)/include/nss_macsec_secy_rx.h $(1)/usr/include/qca-macsec
	$(CP) $(PKG_BUILD_DIR)/include/nss_macsec_secy_tx.h $(1)/usr/include/qca-macsec
	$(CP) $(PKG_BUILD_DIR)/libfal.so $(1)/usr/lib
endef

define Package/$(PKG_NAME)/install
	$(INSTALL_DIR) $(1)/usr/sbin
	$(INSTALL_DIR) $(1)/usr/lib
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/*.so $(1)/usr/lib/
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/macsec_shell $(1)/usr/sbin/
endef

$(eval $(call BuildPackage,$(PKG_NAME)))
