require /home/emre/poky/meta/recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL:append = "libstdc++ mtd-utils"
IMAGE_INSTALL:append = " openssh openssl openssh-sftp-server sudo apt"
IMAGE_INSTALL:append = " libunwind icu libcurl openssl"
IMAGE_INSTALL:append = " curl"
IMAGE_INSTALL:append = " busybox"
IMAGE_INSTALL:append = " htop"
PACKAGECONFIG_pn-curl = "vers krb5 ssl zlib ipv6"
inherit extrausers
EXTRA_USERS_PARAMS = "usermod -P 123 emre;"

IMAGE_ROOTFS_EXTRA_SPACE = "5242880"
