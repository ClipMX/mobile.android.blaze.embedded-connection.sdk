package com.payclip.blaze.embedded_connection.embedded_connection_sdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.payclip.blaze.embedded_connection.embedded_connection_sdk.ui.theme.EmbeddedconnectionsdkTheme
import com.payclip.blaze.embedded_connection.sdk.modules.dummy.domain.DummyObj

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmbeddedconnectionsdkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val dummyObj = remember { DummyObj.instance() }

                    Greeting(
                        name = dummyObj.getDummyString(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmbeddedconnectionsdkTheme {
        Greeting("Android")
    }
}