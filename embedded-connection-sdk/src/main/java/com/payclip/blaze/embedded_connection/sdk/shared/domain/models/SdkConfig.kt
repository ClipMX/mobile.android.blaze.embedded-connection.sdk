package com.payclip.blaze.embedded_connection.sdk.shared.domain.models

data class SdkConfig(
    var appVersion: String = "",
    var codeVersion: Int = -1,
    var bundleId: String = "",
    var appName: String = "",
    var readerType: String? = null
)