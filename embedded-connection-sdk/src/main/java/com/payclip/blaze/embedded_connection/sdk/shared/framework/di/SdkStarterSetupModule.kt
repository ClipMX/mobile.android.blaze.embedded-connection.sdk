package com.payclip.blaze.embedded_connection.sdk.shared.framework.di

import com.payclip.blaze.embedded_connection.sdk.shared.framework.models.EmbeddedConnectionSDK
import com.payclip.blaze.embedded_connection.sdk.shared.domain.models.SdkConfig
import com.payclip.blaze.embedded_connection.sdk.shared.useCases.DefineClipAppInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SdkStarterSetupModule {

    @Provides
    @Singleton
    fun provideMyEmbeddedConnectionSDKBuilder(
        sdkConfig: SdkConfig,
        defineClipAppInfoUseCase: DefineClipAppInfoUseCase
    ): EmbeddedConnectionSDK.Builder {
        return EmbeddedConnectionSDK.Builder(sdkConfig, defineClipAppInfoUseCase)
    }
}