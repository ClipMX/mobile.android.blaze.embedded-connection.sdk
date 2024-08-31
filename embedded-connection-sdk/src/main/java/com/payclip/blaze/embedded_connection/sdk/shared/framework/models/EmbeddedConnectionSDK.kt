package com.payclip.blaze.embedded_connection.sdk.shared.framework.models

import android.content.Context
import com.payclip.blaze.commons.device.DeviceService
import com.payclip.blaze.embedded_connection.sdk.shared.domain.exceptions.SdkNotInitializedException
import com.payclip.blaze.embedded_connection.sdk.shared.domain.models.SdkConfig
import com.payclip.blaze.embedded_connection.sdk.shared.useCases.DefineClipAppInfoUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmbeddedConnectionSDK internal constructor(private var sdkConfig: SdkConfig) {


    fun check(): String {
        return if (sdkConfig.readerType == null) {
            throw SdkNotInitializedException()
        } else {
            "Check sdkInfo: $sdkConfig"
        }
    }


    class Builder @Inject constructor(
        private val sdkConfig: SdkConfig,
        private val defineClipAppInfoUseCase: DefineClipAppInfoUseCase
    ) {

        private var applicationContext: Context? = null

        fun setApplicationContext(applicationContext: Context): Builder {
            this.applicationContext = applicationContext
            return this
        }

        fun build(): EmbeddedConnectionSDK {
            if (applicationContext == null) {
                throw SdkNotInitializedException()
            } else {
                applicationContext?.let { appCtxt ->
                    val packageInfo =
                        appCtxt.packageManager.getPackageInfo(appCtxt.packageName, 0)
                    sdkConfig.appVersion = packageInfo.versionName
                    sdkConfig.codeVersion = packageInfo.versionCode
                    sdkConfig.bundleId = appCtxt.packageName
                    sdkConfig.appName =
                        appCtxt.applicationInfo.loadLabel(appCtxt.packageManager)
                            .toString()
                    sdkConfig.readerType = DeviceService.device.name

                    defineClipAppInfoUseCase.execute()
                }
            }

            return EmbeddedConnectionSDK(sdkConfig)
        }
    }


}