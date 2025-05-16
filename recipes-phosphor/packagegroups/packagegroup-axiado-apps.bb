SUMMARY = "OpenBMC for Axidao - Applications"
PR = "r1"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
        ${PN}-chassis \
        ${PN}-fans \
        ${PN}-flash \
        ${PN}-system \
        "

PROVIDES += "virtual/obmc-chassis-mgmt"
PROVIDES += "virtual/obmc-fan-mgmt"
PROVIDES += "virtual/obmc-flash-mgmt"
PROVIDES += "virtual/obmc-system-mgmt"

RPROVIDES:${PN}-chassis += "virtual-obmc-chassis-mgmt"
RPROVIDES:${PN}-fans += "virtual-obmc-fan-mgmt"
RPROVIDES:${PN}-flash += "virtual-obmc-flash-mgmt"
RPROVIDES:${PN}-system += "virtual-obmc-system-mgmt"

SUMMARY:${PN}-chassis = "Axiado Chassis"
RDEPENDS:${PN}-chassis = " \
        "

SUMMARY:${PN}-fans = "Axiado Fans"
RDEPENDS:${PN}-fans = " \
        "

SUMMARY:${PN}-flash = "Axidao Flash"
RDEPENDS:${PN}-flash = " \
        tdfu \
        "

SUMMARY:${PN}-system = "Axiado System"
RDEPENDS:${PN}-system = " \
        util-linux \
        util-linux-mkfs \
        sysproxy \
        tcu-reset \
        "
