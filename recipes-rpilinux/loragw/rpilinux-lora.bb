SUMMARY = "rpilinux-lora"
DESCRIPTION = "LoraWAN gateway HAL"
LICENSE = "MIT"

do_compile() {
 oe_runmake PREFIX=${THISDIR}/lora-hal
}

do_install() {
  #oe_runmake install PREFIX=${THISDIR}/lora-hal
}
