# Copyright (c) 2021-22 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

SUMMARY = "Axiado uboot recipe"
SECTION = "bootloaders"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=4;md5=744e7e3bb0c94b4b9f6b3db3bf893897"

PROVIDES += "u-boot"

SRC_URI = "file://README"
SRC_URI += "file://u-boot.bin"
SRC_URI += "file://u-boot-axiado-initial-env"
SRC_URI += "file://fw_env.config"

addtask do_deploy after do_unpack
addtask do_install after do_unpack

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/
}

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}
    cp ${WORKDIR}/u-boot.bin ${DEPLOY_DIR_IMAGE}/u-boot.bin
    cp ${WORKDIR}/u-boot-axiado-initial-env ${DEPLOY_DIR_IMAGE}/u-boot-axiado-initial-env
}

FILES:${PN} += "${sysconfdir}/fw_env.config"
