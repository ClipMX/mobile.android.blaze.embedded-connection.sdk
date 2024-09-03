package com.payclip.blaze.emc.embedded_connection_sdk

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.payclip.blaze.emc.embedded_connection_sdk.ui.theme.EmbeddedconnectionsdkTheme
import com.payclip.blaze.emc.sdk.modules.printer.domain.ClipPrinter

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmbeddedconnectionsdkTheme {
                val clipPrinter =
                    remember { ClipPrinter.Builder().setContext(applicationContext).build() }

                val scrollState = rememberScrollState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = clipPrinter.check(),
                        modifier = Modifier.padding(innerPadding),
                    )

                    if (scrollState.canScrollBackward) {
                        HorizontalDivider()
                    }

                    FlowRow(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .verticalScroll(scrollState)
                                .padding(24.dp),
                    ) {
                        PrinterButton(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp)
                                    .padding(top = 24.dp)
                                    .height(56.dp)
                                    .fillMaxWidth(),
                            context = applicationContext,
                        )
                    }

                    if (scrollState.canScrollForward) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Composable
fun PrinterButton(
    modifier: Modifier = Modifier,
    context: Context,
) {
    val clipPrinter = remember { ClipPrinter.Builder().setContext(context).build() }

    Button(
        modifier = modifier,
        onClick = {
            clipPrinter.print("HELLO WORLD FROM FORKING")
        },
    ) {
        Text(text = "Print")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmbeddedconnectionsdkTheme {
        Greeting("Android")
    }
}
