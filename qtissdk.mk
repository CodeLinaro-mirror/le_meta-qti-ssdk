include $(INCLUDE_DIR)/target.mk

QTISSDK:=kmod-qca-phy

ifeq ($(BOARD),sdx75)
  QTISSDK+= macsec-shell
else ifeq ($(BOARD),sdx35)
  QTISSDK+=
endif

ifneq ($(PRPL_VERSION),)
	QTISSDK:=
endif
