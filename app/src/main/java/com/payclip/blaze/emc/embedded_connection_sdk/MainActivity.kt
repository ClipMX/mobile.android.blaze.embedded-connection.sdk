package com.payclip.blaze.emc.embedded_connection_sdk

import android.graphics.Bitmap
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.payclip.blaze.emc.embedded_connection_sdk.ui.theme.EmbeddedconnectionsdkTheme
import com.payclip.blaze.emc.sdk.modules.printer.core.ClipPrinter
import com.payclip.blaze.emc.sdk.modules.printer.core.PrintableContent
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Divider
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Heading
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.ItemList
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.LineBreak
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.ListPrintable
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.PrintableImage
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.RowContent
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Section
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmbeddedconnectionsdkTheme {
                val scrollState = rememberScrollState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "CLIP PRINTER ",
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
                        val context = LocalContext.current
                        PrinterButton(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp)
                                    .padding(top = 24.dp)
                                    .height(56.dp)
                                    .fillMaxWidth(),
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
fun PrinterButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bitmap = context.getDrawable(R.drawable.ic_food_restaurant_foreground)?.toBitmap()
    val secondBitmap = context.getDrawable(R.drawable.ic_launcher_foreground)?.toBitmap()

    val clipPrinter = ClipPrinter.Builder().setContext(context).build()
    Button(
        modifier = modifier,
        onClick = {
            clipPrinter.print(printableContent(bitmap, secondBitmap))
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

val itemList =
    ListPrintable(
        listOf(
            ItemList(rowContent = RowContent("U. Producto", "Precio", isListHeader = true)),
            ItemList(rowContent = RowContent("1 Milk", "$34")),
            ItemList(rowContent = RowContent("1 Orange", "$34")),
            ItemList("Netus luctus massa habitasse"),
            ItemList("Interdum eleifend iaculis;"),
            ItemList("Rhoncus donec quam torquent"),
            ItemList(rowContent = RowContent("1 Coffee", "$34")),
        ),
    )

val itemListWithDivider =
    ListPrintable(
        listOf(
            ItemList(rowContent = RowContent("Left", "Right", isListHeader = true)),
            ItemList(rowContent = RowContent("1 Product", "$34")),
            ItemList(rowContent = RowContent("1 Product", "$34")),
            ItemList("Netus luctus massa habitasse"),
            ItemList("Interdum eleifend iaculis;"),
            ItemList("Rhoncus donec quam torquent"),
        ),
        divider = Divider(),
    )

val footerList =
    listOf(
        "Aplican términos y condiciones",
        "Conserve el ticket para futuras aclaraciones",
        "clip.mx",
    )

fun printableContent(
    bitmap: Bitmap?,
    secondImage: Bitmap?,
): PrintableContent =
    PrintableContent
        .Builder()
        .setHeader("CLIP PRINTER SDK", bitmap)
        .addPrintable(
            Section(
                text = "Total:",
                alignment = TextAlignment.CENTER,
            ),
        ).addPrintable(
            Heading(
                "$25.00",
                alignment = TextAlignment.CENTER,
                fontSize = FontSize.SUPER_LARGE,
            ),
        ).addPrintable(
            Heading(
                text = "VENTA",
                alignment = TextAlignment.CENTER,
                fontSize = FontSize.MEDIUM,
            ),
        ).addPrintable(
            Section(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing",
                alignment = TextAlignment.CENTER,
            ),
        ).addPrintable(Divider())
        .addPrintable(
            Heading(
                text = "TRANSACCIÓN APROBADA",
                alignment = TextAlignment.CENTER,
                lineBreaksTop = 1,
            ),
        ).addPrintable(PrintableImage(secondImage!!))
        .addPrintable(Divider(title = "COPY OF RECEIPT"))
        .addPrintable(itemListWithDivider)
        .addPrintable(LineBreak(4))
        .addPrintable(itemList)
        .setFooter(footerList)
        .build()
