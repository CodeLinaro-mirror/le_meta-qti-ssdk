include $(INCLUDE_DIR)/target.mk

QTISSDK:=kmod-qca-phy

ifeq ($(BOARD),sdx75)
  QTISSDK+=
else ifeq ($(BOARD),sdx35)
  QTISSDK+=
endif
