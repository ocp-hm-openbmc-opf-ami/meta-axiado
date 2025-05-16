FILESEXTRAPATHS:append := ":${THISDIR}/${PN}"
                 
AXIADO_KERNEL_VERSION = "6.6.16-yocto-s-dirty-c8e325f"
KERNEL_VERSION = "${AXIADO_KERNEL_VERSION}"

SRC_URI += " file://defconfig \
             file://fitImage \
             file://${AXIADO_KERNEL_VERSION}.tar \
           "

do_install:append() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}
    cp -r ${WORKDIR}/${KERNEL_VERSION}/* ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}
}

do_deploy() {
    cp ${WORKDIR}/fitImage ${DEPLOY_DIR_IMAGE}/fitImage
}
