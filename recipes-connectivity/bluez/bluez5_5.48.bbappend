FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

## Disable Patches
SRC_URI_remove = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'enable-rdkv-bt-voice', 'file://0001-hciattach-bcm43xx-fix-the-delay-timer-for-firmware-d.patch', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'enable-rdkv-bt-voice', 'file://cve-2017-1000250.patch', '', d)} \
    "
## Patches ported by SkyQ
SRC_URI += " ${@bb.utils.contains('DISTRO_FEATURES', 'enable-rdkv-bt-voice', ' \
    file://breakpad.patch \
    file://bluez-5.48-002-disable-unneeded-plugins.patch \
    file://bluez-5.48-003-add-configurable-char-write-value-options.patch \
    file://bluez-5.48-004-disable-ble-battery-profile.patch \
    file://bluez-5.48-005-enable_auto_connect_on_all_disconnects.patch \
    file://bluez-5.48-006-change_cache_clear_timeout.patch \
    file://bluez-5.48-007-disable_sigpipe_signal.patch \
    file://bluez-5.48-008-make-storage-dir-runtime-configurable.patch \
    file://bluez-5.48-009-fix-gatt-characteristic-crash-on-remove.patch \
    file://bluez-5.48-010-add-configurable-kernel-connection-control.patch \
    file://bluez-5.48-011-add-configurable-secure-connections.patch \
    file://bluez-5.48-012-add-configurable-power-off-on-startup.patch \
    file://bluez-5.48-013-add-configurable-att-le-mtu.patch \
    file://bluez-5.48-014-add-configurable-delayed-gatt-connection.patch \
    file://bluez-5.48-015-delete-autoconnect-on-remove.patch\
    file://bluez-5.48-016-disable-irk-storage-for-sky-devices.patch \
    file://bluez-5.48-017-bluetooth_configuration_hardening.patch \
    file://bluez-5.48-019-improve_firmware_upload_ruwido.patch \
    file://bluez-5.48-020-main_configuration_hardening.patch \
    file://bluez-5.48-021-set-bdaddr-in-phys-uhid.patch \
    file://bluez-5.48-022-enable_bdaddr.patch \
    file://bluez-5.48-023-enable_discovery_filter.patch \
    file://bluez-5.48-024-add-remote-device-name-change-event-handler.patch \
    file://bluez-5.48-025-add-hog-service-for-skyq-rcus.patch \
    file://bluez-5.48-026-disable-bluez-powercyle-respawn.patch \
    file://bluez-5.48-027-free-discovery-reply-on-error.patch \
    file://bluez-5.48-028-crash-in-gatt-client-callback.patch \
    file://bluez-5.48-029-create_storage_directory_before_starting_service.patch \
    file://bluez-5.48-032-restore-file-AMLOGIC-1276.patch \
    file://bluez-5.48-033-enable-debug-logging.patch \
    file://0001-testtools-fix-SIOCGSTAMP-undeclared-error.patch \
    file://0002-libexecdir-location.patch \
    ', '', d)} \
	"

## Disabled SkyQ Patches
#file://bluez-5.48-018-change_storage_dir.patch

SRC_URI_append_hybrid += " ${@bb.utils.contains('DISTRO_FEATURES', 'enable-rdkv-bt-voice', '', 'file://0001-bluetooth_autoenable_policy_main_conf.patch', d)}"
SRC_URI_append_client += " ${@bb.utils.contains('DISTRO_FEATURES', 'enable-rdkv-bt-voice', '', 'file://0001-bluetooth_autoenable_policy_main_conf.patch', d)}"

