# Copyright (c) 2021-22 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

SUMMARY = "Dual Boot State Management"
DESCRIPTION = "Service to mark boot as successful for dual boot system"
LICENSE = "CLOSED"
PV = "1.0"

LIC_FILES_CHKSUM ?= "file://${WORKDIR}/COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

inherit systemd
RDEPENDS:${PN} = "bash libubootenv-bin"

S = "${WORKDIR}"

SRC_URI += "file://boot-state.service \
            file://COPYING.axiado"

SYSTEMD_SERVICE:${PN} = "boot-state.service"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/boot-state.service ${D}${systemd_system_unitdir}
}
