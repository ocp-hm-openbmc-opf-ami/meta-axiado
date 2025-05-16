## Table of Contents

- Adding the meta-axiado layer to your build
- Adding the DTB

### Adding the meta-Axiado layer to your build

```
git clone https://github.com/ocp-hm-openbmc-opf-ami/meta-axiado
meta-ami/github-gitlab-url.sh
. setup evk-axiado
bitbake obmc-phosphor-image
```
### Adding the DTB
1. add DTBs to machine conf (evb-axiado.conf for example) and then update BMC firmware
```
KERNEL_DEVICETREE = \
    "axiado/axiado-1.dtb \
     axiado/axiado-2.dtb \
     axiado/intel-beechnut.dtb "
```
2. Optional: Set platform DTB name to the fitconfig
```
# in uboot
setenv fdt_conf conf-intel-beechnut.dtb
saveenv
# in user space
fw_setenv fdt_conf conf-intel-beechnut.dtb
```
