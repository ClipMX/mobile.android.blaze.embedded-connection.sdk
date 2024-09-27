package com.payclip.blaze.emc.embedded_connection_sdk.ui.screens.home

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import com.payclip.blaze.emc.embedded_connection_sdk.R
import com.payclip.blaze.emc.sdk.modules.printer.core.ClipPrinter
import com.payclip.blaze.emc.sdk.modules.printer.core.PrintableContent
import com.payclip.blaze.emc.sdk.modules.printer.domain.listeners.ClipPrinterListener
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Divider
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Heading
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.ItemList
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.LineBreak
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.ListPrintable
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.PrintableImage
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.RowContent
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Section
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.ClipPrinterError
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private lateinit var clipPrinter: ClipPrinter
    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.WaitingAction)
    val state: StateFlow<HomeScreenState>
        get() = _state

    fun print(context: Context) {
        _state.value = HomeScreenState.Printing

        clipPrinter = ClipPrinter.Builder().setContext(context).build()
        val bitmap = context.getDrawable(R.drawable.ic_food_restaurant_foreground)?.toBitmap()
        val secondBitmap = context.getDrawable(R.drawable.ic_launcher_foreground)?.toBitmap()
        clipPrinter.print(
            printableContent(bitmap, secondBitmap),
            object : ClipPrinterListener {
                override fun onSuccessfulPrint() {
                    _state.value = HomeScreenState.PrintingSuccess
                }

                override fun onFailedPrint(clipPrinterError: ClipPrinterError) {
                    _state.value = HomeScreenState.PrintingError(clipPrinterError.name)

                }
            },
        )

    }


    private val itemList =
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

    private val itemListWithDivider =
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

    private val footerList =
        listOf(
            "Aplican términos y condiciones",
            "Conserve el ticket para futuras aclaraciones",
            "clip.mx",
        )

    private fun printableContent(
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

}

sealed class HomeScreenState {
    object WaitingAction : HomeScreenState()
    object Printing : HomeScreenState()
    class PrintingError(val error: String) : HomeScreenState()
    object PrintingSuccess : HomeScreenState()
}