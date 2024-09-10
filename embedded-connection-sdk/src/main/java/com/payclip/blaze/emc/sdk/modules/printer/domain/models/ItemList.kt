package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.emc.sdk.modules.printer.domain.exceptions.ItemListContentException
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toAlignMode
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toBlazeFontSize
import com.payclip.blaze.printer.core.domain.AlignMode
import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable item list that can be either a single line of text or a two-column row.
 *
 * @param text The text to be printed as a single line. Default is an empty string.
 * @param boldEnabled Indicates whether the text should be printed in bold. Default is false.
 * @param rowContent The content of the two-column row. Default is null.
 *
 * @throws ItemListContentException if both `text` and `rowContent` are provided.
 */
class ItemList(
    override var text: String = "",
    override var boldEnabled: Boolean = false,
    var rowContent: RowContent? = null,
) : PrintableText(
        text = text,
        alignment = TextAlignment.LEFT,
        boldEnabled = boldEnabled,
        fontSize = FontSize.SMALL,
    ) {
    private lateinit var devicePrinter: Printer

    init {
        if (text.isNotBlank() && rowContent != null) {
            throw ItemListContentException(this)
        }
    }

    /**
     * Appends the item list to the printer output.
     *
     * If `text` is not empty, it prints a single line of text with the specified formatting.
     * If `rowContent` is provided, it prints a two-column row with appropriate spacing and formatting.
     *
     * @param devicePrinter The printer device to append the item list to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        this.devicePrinter = devicePrinter

        if (text.isNotBlank()) {
            devicePrinter.appendText(
                text = text,
                fontSize = fontSize.toBlazeFontSize(),
                alignment = alignment.toAlignMode(),
                isBoldFont = boldEnabled,
            )
        } else {
            rowContent?.let {
                boldEnabled = it.isListHeader
                printSeparate(it.textColumn1, it.textColumn2, boldEnabled, it.isListHeader)
            }
        }
    }

    /**
     * Prints a two-column row with appropriate spacing and formatting.
     *
     * @param text1 The text for the first column.
     * @param text2 The text for the second column.
     * @param isBold Indicates whether the text should be printed in bold. Default is false.
     * @param biggerLetter Indicates whether the text should be printed with a larger font size. Default is false.
     */
    private fun printSeparate(
        text1: String,
        text2: String,
        isBold: Boolean = false,
        biggerLetter: Boolean = false,
    ) {
        val maxLen = if (isBold && biggerLetter) 29 else 32
        val textsLen = text1.length + text2.length
        val spaceBetween =
            if (textsLen > maxLen) {
                1
            } else {
                maxLen - textsLen
            }
        val space = " ".repeat(spaceBetween)
        val separatedText = text1.plus(space).plus(text2)
        devicePrinter.appendText(
            text = separatedText,
            fontSize =
                if (isBold &&
                    biggerLetter
                ) {
                    com.payclip.blaze.printer.core.domain.FontSize.MEDIUM
                } else {
                    com.payclip.blaze.printer.core.domain.FontSize.SMALL
                },
            alignment = AlignMode.LEFT,
            isBoldFont = isBold,
        )
    }
}
