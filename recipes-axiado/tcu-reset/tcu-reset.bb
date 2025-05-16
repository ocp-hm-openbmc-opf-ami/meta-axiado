# Copyright (c) 2021-22 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

DESCRIPTION = "TCU reset package"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM ?= "file://${WORKDIR}/COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += "file://ax3000-reboot.service \
            file://tcu-reset.sh \
            file://COPYING.axiado"

inherit systemd

SYSTEMD_SERVICE:${PN} = "ax3000-reboot.service"

do_install() {
    install -d ${D}${bindir}
    install -m 0777 ${WORKDIR}/tcu-reset.sh ${D}${bindir}/

    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/ax3000-reboot.service ${D}${systemd_unitdir}/system
}

RDEPENDS:${PN} = "bash"
