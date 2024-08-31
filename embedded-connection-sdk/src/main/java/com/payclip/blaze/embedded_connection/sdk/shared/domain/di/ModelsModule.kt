package com.payclip.blaze.embedded_connection.sdk.shared.domain.di

import com.payclip.blaze.embedded_connection.sdk.shared.domain.models.SdkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModelsModule {

    @Provides
    @Singleton
    fun provideSdkConfig(): SdkConfig {
        return SdkConfig()
    }

}