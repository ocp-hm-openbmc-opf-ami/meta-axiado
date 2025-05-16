# Copyright (c) 2021-22 Axiado Corporation (or its affiliates). All rights reserved.
# Use, modification and redistribution of this file is subject to your possession
# of a valid End User License Agreement (EULA) for the Axiado Product of which
# these sources are part of and your compliance with all applicable terms and
# conditions of such licence agreement.

inherit image_types_phosphor

do_generate_ext4_tar() {
    # Generate the U-Boot image
    mk_empty_image_zeros image-u-boot ${MMC_UBOOT_SIZE}
    do_generate_image_uboot_file image-u-boot

    # Generate a compressed ext4 filesystem with the fitImage file in it to be
    # flashed to the boot partition of the eMMC
    install -d boot-image
    install -m 644 ${DEPLOY_DIR_IMAGE}/${FLASH_KERNEL_IMAGE} boot-image/fitImage
    mk_empty_image_zeros boot-image.${FLASH_EXT4_BASETYPE} ${MMC_BOOT_PARTITION_SIZE}
    mkfs.ext4 -F -i 4096 -d boot-image boot-image.${FLASH_EXT4_BASETYPE}

    # Error codes 0-3 indicate successfull operation of fsck
    fsck.ext4 -pvfD boot-image.${FLASH_EXT4_BASETYPE} || [ $? -le 3 ]
    zstd -f -k -T0 -c ${ZSTD_COMPRESSION_LEVEL} boot-image.${FLASH_EXT4_BASETYPE} > boot-image.${FLASH_EXT4_BASETYPE}.zst

    # Generate the compressed ext4 rootfs
    zstd -f -k -T0 -c ${ZSTD_COMPRESSION_LEVEL} ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.${FLASH_EXT4_BASETYPE} > ${IMAGE_LINK_NAME}.${FLASH_EXT4_BASETYPE}.zst
    ln -sf boot-image.${FLASH_EXT4_BASETYPE}.zst image-kernel
    ln -sf ${IMAGE_LINK_NAME}.${FLASH_EXT4_BASETYPE}.zst image-rofs
    ln -sf ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.rwfs.${FLASH_EXT4_OVERLAY_BASETYPE} image-rwfs
    ln -sf ${S}/MANIFEST MANIFEST
    ln -sf ${S}/publickey publickey

    make_signatures image-u-boot image-kernel image-rofs image-rwfs MANIFEST publickey
    make_tar_of_images ext4.mmc MANIFEST publickey ${signature_files}
}
