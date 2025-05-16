# Copyright (c) 2021-23 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.
SUMMARY = "Sysproxy"
SECTION = "Sysproxy application"
LICENSE = "CLOSED"
PV = "1.0"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=01d0d9bdb04606d39dcbff1ca352f133"

DEPENDS = "cmake-native"

inherit obmc-phosphor-systemd

SRC_URI = "file://COPYING.axiado"
SRC_URI += "file://sysproxy.service"
SRC_URI += "file://sysmgr_proxy"

SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_SERVICE:${PN} = "sysproxy.service"

do_install() {
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/sysproxy.service ${D}/${systemd_unitdir}/system
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/sysmgr_proxy ${D}${bindir}
}
