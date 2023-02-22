include $(INCLUDE_DIR)/target.mk

ifeq ($(BOARD),sdx75)
	QTISSDK+= kmod-qca-phy
endif
