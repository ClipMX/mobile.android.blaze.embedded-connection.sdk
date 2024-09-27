package com.payclip.blaze.emc.embedded_connection_sdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.payclip.blaze.emc.embedded_connection_sdk.ui.screens.home.HomeScreen
import com.payclip.blaze.emc.embedded_connection_sdk.ui.theme.EmbeddedconnectionsdkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmbeddedconnectionsdkTheme {
                HomeScreen()
            }
        }
    }
}

