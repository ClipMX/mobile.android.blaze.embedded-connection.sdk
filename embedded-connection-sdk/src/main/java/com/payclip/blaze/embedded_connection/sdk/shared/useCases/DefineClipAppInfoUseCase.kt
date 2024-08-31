package com.payclip.blaze.embedded_connection.sdk.shared.useCases

import com.payclip.blaze.commons.device.Device
import com.payclip.blaze.commons.device.DeviceService
import com.payclip.blaze.commons_hardware.shared.ClipAppInfo
import com.payclip.blaze.commons_hardware.shared.ClipAppInfoService
import com.payclip.blaze.commons_hardware.shared.ClipReaderType
import com.payclip.blaze.commons_hardware.shared.ReaderTypeEnum
import com.payclip.blaze.embedded_connection.sdk.shared.domain.models.SdkConfig
import javax.inject.Inject

class DefineClipAppInfoUseCase @Inject constructor(private val sdkConfig: SdkConfig) {

    fun execute() {
        val clipAppInfo = ClipAppInfo(
            appVersion = sdkConfig.appVersion,
            codeVersion = sdkConfig.codeVersion,
            bundleId = sdkConfig.bundleId,
        )
        ClipAppInfoService.save(appInfo = clipAppInfo)


        ClipReaderType.save(DeviceService.device.type)
        val readerType = ClipReaderType.get().getReaderTypeString
        DeviceService.initAppInfo(
            appVersion = sdkConfig.appVersion,
            codeVersion = sdkConfig.codeVersion,
            bundleId = sdkConfig.bundleId,
            appName = sdkConfig.appName,
            readerType = readerType,
        )
    }
}

val ReaderTypeEnum.getReaderTypeString: String
    get() = when (this) {
        ReaderTypeEnum.BORON -> "boron_reader"
        ReaderTypeEnum.NITROGEN -> "nitrogen_reader"
        ReaderTypeEnum.SODIUM -> "sodium_reader"
        ReaderTypeEnum.NO_READER -> "standard_readers"
    }

val Device.type: ReaderTypeEnum
    get() = when (this) {
        Device.PRO, Device.STAND -> ReaderTypeEnum.BORON
        Device.TOTAL -> ReaderTypeEnum.NITROGEN
        Device.PRO2, Device.TOTAL2 -> ReaderTypeEnum.SODIUM
        else -> ReaderTypeEnum.NITROGEN
    }