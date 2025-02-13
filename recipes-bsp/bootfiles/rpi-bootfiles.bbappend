SUMMARY = "Recipe to create bcm2711-bootfiles directory in the image deploy directory that contains overlays and trimmed version of config.txt (also corrects improper naming convention that uses bcm2835, which is an older SoC)."

DESCRIPTION = "Closed source binary files to help boot all raspberry pi devices."
LICENSE = "Broadcom-RPi"

BCM2711_DIR = "bcm2711-bootfiles"

do_after_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}
    for i in ${S}/*.elf ; do
      cp $i ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}
    done

    for i in ${S}/*.dat ; do
      cp $i ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}
    done
    
    cp -r ${RPIFW_S}/boot/overlays/ ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}/overlays
    # Make a simple config.txt
    touch ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}/config.txt
    echo 'kernel=kernel_rpilinux.img' >> ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}/config.txt
    echo 'arm_64bit=1' >> ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}/config.txt
    echo 'enable_uart=1' >> ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}/config.txt
    # Make a simple cmdline.txt
    touch ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}/cmdline.txt
    echo 'dwc_otg.lpm_enable=0 console=serial0,115200 console=ttyS0 root=/dev/mmcblk0p2 rootfstype=ext4 elevator=deadline fsck.repair=yes rootwait' >> ${DEPLOY_DIR_IMAGE}/${BCM2711_DIR}/cmdline.txt
}

addtask after_deploy before do_build after do_install do_deploy
