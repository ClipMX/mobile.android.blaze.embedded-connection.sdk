package com.payclip.blaze.embedded_connection.sdk.shared.framework.providers

import android.content.Context
import com.payclip.blaze.embedded_connection.sdk.shared.framework.models.EmbeddedConnectionSDK
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

object EmbeddedConnectionSDKProvider {
    private lateinit var embeddedConnectionSDK: EmbeddedConnectionSDK

    fun initialize(applicationContext: Context): EmbeddedConnectionSDK {
        if (!EmbeddedConnectionSDKProvider::embeddedConnectionSDK.isInitialized) {
            val builder = EntryPoints.get(
                applicationContext,
                EmbeddedConnectionSDKBuilderEntryPoint::class.java
            ).getEmbeddedConnectionSDKBuilder()

            embeddedConnectionSDK = builder.setApplicationContext(applicationContext).build()
        }

        return embeddedConnectionSDK
    }

}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface EmbeddedConnectionSDKBuilderEntryPoint {
    fun getEmbeddedConnectionSDKBuilder(): EmbeddedConnectionSDK.Builder
}