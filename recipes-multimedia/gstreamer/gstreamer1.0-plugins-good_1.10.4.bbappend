FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0002-qtdemux-add-context-for-a-preferred-protection.patch \
                   file://0003-qtdemux-dont-check-pushbased-edts.patch \
                   file://0004-qtdemux-also-push-buffers-without-encryption-info-in.patch \
                   file://0005-qtdemux-add-senc-parser.patch \
                   file://0007-Fix-senc-subsample_count-0.patch \
                   file://0008-qtdemux-fix-signle-frame-processing.patch \
                   file://0009-qtdemux-aamp-tm.patch \
                   file://0012-qtdemux-add-atmos-mimetype.patch \
                   file://0013-qtdemux-remove-override-segment-event.patch \
                   file://0010-matroskademux-Allow-Matroska-headers-to-be-read-more.patch \
                   file://0011-matroskademux-Start-stream-time-at-zero.patch \
                   file://0012-matroskademux-emit-no-more-pads-when-the-Tracks-elem.patch \
                   file://0014-qtdemux-clear-crypto-info-on-trak-switch.patch \
                   file://0015-qtdemux-handle-skip-fourcc.patch \
                   file://0017-Clear-protected-flag-on-switching-tracks-encrypted-c.patch \
                   file://0018-matroska-webm-decryption-support.patch \
                   file://0019-matroskdemux-do-not-use-MapInfo.data-after-unmapping.patch \
                   file://0020-matroska-demux-support-for-parsing-HDR-plugin-good.patch \
                   file://0021-qtdemux-aamp-tm-multiperiod.patch \
                   file://0020-Initial-support-for-Dolby-Vision.patch \
                   file://0022-qtdemux-backport-PIFF-track-encryption-box-support.patch \
                   file://0023-matroska-Quiet-a-WARN-when-parsing-push-mode.patch \
                   file://0024-isomp4-Don-t-spam-debug-log-with-knonw-padding-atoms.patch \
                   file://0029-matroskademux-Refactor-track-parsing-out-from-adding.patch \
                   file://0030-matroskademux-Parse-successive-Tracks-elements.patch \
                   file://0031-qtdemux-aamp-fix-mp4a-atom-skip.patch \
                   file://0032-Avoid-sending-EOS-prematurely-for-live-stream.patch \
                   file://0033-Adding-uuid-tag.patch \
                   file://0033-qtdemux-Don-t-crash-in-debug-output-if-stream-NULL.patch \
                   file://0034-qtdemux-dont-error-with-no-protection-events.patch \
                   file://0035-qtdemux-check-ss_info.patch \
                   file://0036-qtdemux-b6e8c6323e01249bb3ac4f121c3adc3ea8d924bf.patch \
                   file://0037-qtdemux-aamp-avoid-unwanted-header-logging.patch \
                   file://0038-Add-support-for-FLAC-encoded-audio.patch \
"

#FIXME causes macroblocking on Tubi (overlapping decoding timestamps), disabled for now
#SRC_URI += "file://0016-qtdemux-Keep-sample-data-from-the-current-fragment-o.patch"

## When SVP is enabled on the Broadcom chip, i.e., XG1v4, need to disable aac audio parser.
SRC_URI_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'sage_svp', 'file://0006-audioparser-remove-aacparse-for-svp.patch', '', d)}"
SRC_URI_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'sage_svp', 'file://0011-audioparser-remove-eac3.patch', '', d)}"
