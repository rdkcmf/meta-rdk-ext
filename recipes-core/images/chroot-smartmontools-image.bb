SUMMARY = "Suitable rootfs to run smartmontools in a chroot"

LICENSE = "MIT"

# that pulls probably more than what's really needed, we might want
# to optimize the package list instead of using core-boot.
IMAGE_INSTALL = "packagegroup-core-boot smartmontools"
IMAGE_LINGUAS = " "

# we only need tar.gz image, as we build a 'chroot' image that will be extracted
# in main image
IMAGE_FSTYPES = "tar.gz"

# we are building a chroot, no we don't need /dev.
USE_DEVFS = "1"

inherit core-image
