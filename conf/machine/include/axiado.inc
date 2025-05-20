# Copyright (c) 2021-24 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

SOC_FAMILY = "axiado-ax3000"
include conf/machine/include/soc-family.inc
include conf/machine/include/arm/arch-arm64.inc

PREFERRED_PROVIDER_virtual/kernel ?= "linux-axiado"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-axiado"
PREFERRED_PROVIDER_u-boot ?= "u-boot-axiado"

# Build var, valid values are dev and prod (production)
# Prod builds eMMC image with wic filesystem and kernel, dtb images
# Dev builds initramfs image with filesystem + kernel in a single image
# For modifying this value, add BUILD_VAR = "dev" in local.conf file under build/<machine>
BUILD_VAR ??= "prod"
AXIADO_INC_FILE := "${@bb.utils.contains('BUILD_VAR', 'dev', 'axiado-dev.inc', 'axiado-prod.inc', d)}"
INCLUDE_PATH := "conf/machine/include/"
require ${INCLUDE_PATH}${AXIADO_INC_FILE}

INITRAMFS_IMAGE_BUNDLE = "1"
IMAGE_CLASSES:append = " image_types_ax3000"

DEFAULTTUNE:ax3000 ?= "aarch64"

KERNEL_IMAGETYPE ?= "fitImage"
FLASH_KERNEL_IMAGE ?= "fitImage"

UBOOT_ENTRYPOINT ?= "0x3E000000"
UBOOT_LOADADDRESS ?= "0x3E000000"
UBOOT_DTB_LOADADDRESS ?= "0x3FF00000"

#this is needed by emmc, avoid removing it.
BAD_RECOMMENDATIONS:remove = "e2fsprogs-e2fsck"
