# Copyright (c) 2021-23 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

SUMMARY = "tdfu"
DESCRIPTION = "TCU Firmware Upgrade Application"
LICENSE = "CLOSED"
PV = "1.0"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=01d0d9bdb04606d39dcbff1ca352f133"

inherit cmake pkgconfig

SRC_URI = "file://COPYING.axiado"
SRC_URI += "file://ax3000-fw-update"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/ax3000-fw-update ${D}${bindir}
}
