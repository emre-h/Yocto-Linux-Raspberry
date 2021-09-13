#
# This is the sx1302_hal apllication recipe
#
##

SUMMARY = "sx1302_hal application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://sx1302hal \
        "
S = "${WORKDIR}/sx1302hal"


do_package_qa[noexec] = "1" 

FILES_${PN} += " \
	 ${libdir}/sx1302hal \
	"

CFLAGS:prepend += " -I${S}/libtools/inc -I${S}/libloragw/inc -I${S}/packet_forwarder/inc"

do_compile() {
        oe_runmake
}

do_install() {
        install -d ${D}${libdir}/sx1302hal
        
        install -m 0755 ${S}/util_chip_id/chip_id ${D}${libdir}/sx1302hal/
        install -m 0755 ${S}/util_boot/boot ${D}${libdir}/sx1302hal/
        install -m 0755 ${S}/packet_forwarder/global_conf* ${D}${libdir}/sx1302hal/
        install -m 0755 ${S}/libloragw/test* ${D}${libdir}/sx1302hal/
        install -m 0755 ${S}/packet_forwarder/lora_pkt_fwd ${D}${libdir}/sx1302hal/
        install -m 0755 ${S}/util_net_downlink/net_downlink ${D}${libdir}/sx1302hal/
        install -m 0755 ${S}/util_spectral_scan/spectral_scan ${D}${libdir}/sx1302hal/
        install -m 0755 ${S}/tools/reset_lgw.sh ${D}${libdir}/sx1302hal/
        
        cd ${D}${libdir}
	ln -s ../lib/sx1302hal/spectral_scan ${D}${libdir}/spectral_scan
}

