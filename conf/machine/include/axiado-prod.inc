# Copyright (c) 2021-24 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

WKS_FILE ?= "emmc-axiado.wks.in"
WKS_FILE_DEPENDS ?= "virtual/kernel"
WKS_FILE_DEPENDS += "e2fsprogs-native"

IMAGE_BOOT_FILES ?= "fitImage"

require conf/distro/include/phosphor-mmc.inc
