package com.payclip.blaze.emc.sdk.shared.domain.utils

import com.payclip.blaze.commons.device.Device
import com.payclip.blaze.commons.device.DeviceService
import com.payclip.blaze.commons_hardware.shared.ClipReaderType
import com.payclip.blaze.commons_hardware.shared.ReaderTypeEnum

internal fun setupReaderDevice() {
    when (DeviceService.device) {
        Device.TOTAL, Device.N80 -> ClipReaderType.save(ReaderTypeEnum.NITROGEN)
        Device.PRO2,
        Device.TOTAL2,
        -> ClipReaderType.save(ReaderTypeEnum.SODIUM)

        Device.PRO, Device.STAND -> ClipReaderType.save(ReaderTypeEnum.BORON)
        else -> ClipReaderType.save(ReaderTypeEnum.NO_READER)
    }
}
