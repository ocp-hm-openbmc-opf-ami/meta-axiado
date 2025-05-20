# Copyright (c) 2021-22 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

SUMMARY = "Networking packages"
DESCRIPTION = "Networking firmware and  services"
LICENSE = "CLOSED"

LIC_FILES_CHKSUM ?= "file://COPYING.axiado;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += "file://firmware_eip207_ifpp.bin \
	    file://firmware_eip207_ipue.bin \
	    file://firmware_eip207_ofpp.bin \	  
	    file://firmware_eip207_opue.bin \	  
	    file://config_eip_ns.sh \	  
	    file://fwl_dmn.py \	  
	    file://eip_fwl.service \	  
	    file://COPYING.axiado \ 
	    file://venice_char \
	    file://stats_1g.sh \
	    file://stat_eip.sh \
	    file://stat_shim_mac.sh \
	    file://stat_phy_vsc.sh \
	    file://stat_phy_mvl.sh \
	    file://stat_hw_fwl.sh"

S = "${WORKDIR}"

inherit obmc-phosphor-systemd
DEPENDS += "systemd"
RDEPENDS:${PN} += "bash"

do_install() {
        install -d ${D}/lib/firmware/
        install -m 0777 firmware_eip207_ifpp.bin ${D}/lib/firmware/
        install -m 0777 firmware_eip207_ipue.bin ${D}/lib/firmware/
        install -m 0777 firmware_eip207_ofpp.bin ${D}/lib/firmware/
        install -m 0777 firmware_eip207_opue.bin ${D}/lib/firmware/

        install -d ${D}/etc/eip/scripts/
        install -m 0777 config_eip_ns.sh ${D}/etc/eip/scripts/
        install -m 0777 fwl_dmn.py ${D}/etc/eip/scripts/
        install -m 0777 venice_char ${D}/etc/eip/scripts/
        install -m 0777 stats_1g.sh ${D}/etc/eip/scripts/
        install -m 0777 stat_eip.sh ${D}/etc/eip/scripts/
        install -m 0777 stat_shim_mac.sh ${D}/etc/eip/scripts/
        install -m 0777 stat_phy_vsc.sh ${D}/etc/eip/scripts/
        install -m 0777 stat_phy_mvl.sh ${D}/etc/eip/scripts/
        install -m 0777 stat_hw_fwl.sh ${D}/etc/eip/scripts/
}

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"

SYSTEMD_SERVICE:${PN} = "eip_fwl.service"

FILES:${PN} += "/lib/firmware/"
FILES:${PN} += "/etc/eip/scripts/"
FILES:${PN} += "/lib/firmware/firmware_eip207_ifpp.bin"
FILES:${PN} += "/lib/firmware/firmware_eip207_ipue.bin"
FILES:${PN} += "/lib/firmware/firmware_eip207_ofpp.bin"
FILES:${PN} += "/lib/firmware/firmware_eip207_opue.bin"
FILES:${PN} += "/etc/eip/scripts/config_eip_ns.sh"
FILES:${PN} += "/etc/eip/scripts/fwl_dmn.py"
FILES:${PN} += "/etc/eip/scripts/stats_1g.sh"
FILES:${PN} += "/etc/eip/scripts/stat_eip.sh"
FILES:${PN} += "/etc/eip/scripts/stat_shim_mac.sh"
FILES:${PN} += "/etc/eip/scripts/stat_phy_vsc.sh"
FILES:${PN} += "/etc/eip/scripts/stat_phy_mvl.sh"
FILES:${PN} += "/etc/eip/scripts/stat_hw_fwl.sh"

RDEPENDS_${PN} += "bash"
INSANE_SKIP:${PN} += "usrmerge"
