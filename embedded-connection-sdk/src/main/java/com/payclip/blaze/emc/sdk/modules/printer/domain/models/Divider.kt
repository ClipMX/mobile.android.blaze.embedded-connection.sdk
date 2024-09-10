package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toAlignMode
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toBlazeFontSize
import com.payclip.blaze.printer.core.domain.AlignMode
import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable divider, which can be a simple line or a line with a centered title.
 *
 * @param divider The character used to create the divider line. Default is '-'.
 * @param title An optional title to be centered within the divider. Default is null.
 */
class Divider(
    var divider: Char = '-',
    var title: String? = null,
) : PrintableText(
        text = divider.toString(),
        alignment = TextAlignment.CENTER,
        boldEnabled = false,
        fontSize = FontSize.MEDIUM,
    ) {
    private lateinit var devicePrinter: Printer

    /**
     * Appends the divider to the printer output.
     *
     * If a title is provided, it prints a divider with the title centered.
     * Otherwise, it prints a simple line divider.
     *
     * @param devicePrinter The printer device to append the divider to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        this.devicePrinter = devicePrinter

        if (title != null) {
            printDividerWithText()
        } else {
            printLineDivider()
        }
    }

    /**
     * Prints a simple line divider using the specified character and length.
     */
    private fun printLineDivider() {
        devicePrinter.appendText(
            text = divider.toString().repeat(TIMES),
            fontSize = fontSize.toBlazeFontSize(),
            alignment = alignment.toAlignMode(),
            isBoldFont = boldEnabled,
        )
    }

    /**
     * Prints a divider with the title centered within the line.
     */
    private fun printDividerWithText() {
        val text = title
        val textLen = text?.length ?: 0
        val mustFill = (TIMES_WITH_TEXT - textLen).div(2)
        val filler = divider.toString().repeat(mustFill)
        val dividerWithText = filler + text + filler

        devicePrinter.appendText(
            text = dividerWithText,
            fontSize = com.payclip.blaze.printer.core.domain.FontSize.SMALL,
            alignment = AlignMode.CENTER,
            isBoldFont = false,
        )
    }

    companion object {
        const val TIMES = 29
        const val TIMES_WITH_TEXT = 32
    }
}
