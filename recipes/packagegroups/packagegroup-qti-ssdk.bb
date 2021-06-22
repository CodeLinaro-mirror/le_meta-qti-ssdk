SUMMARY = "Package suite for SSDK Switch Driver"
LICENSE = "BSD-3-Clause"
inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = ' \
    packagegroup-qti-ssdk \
'

RDEPENDS_packagegroup-qti-ssdk = ' \
    qca-ssdk-nohnat \
    qca-ssdk-shell \
    '