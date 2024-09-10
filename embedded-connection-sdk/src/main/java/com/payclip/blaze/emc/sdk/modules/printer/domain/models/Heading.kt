package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toAlignMode
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toBlazeFontSize
import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable heading with text, alignment, font size, and optional top line breaks.
 *
 * @param text The text content of the heading.
 * @param alignment The text alignment for the heading. Default is `TextAlignment.LEFT`.
 * @param fontSize The font size for the heading. Default is `FontSize.LARGE`.
 * @param lineBreaksTop The number of line breaks to add before the heading. Default is 0.
 */
class Heading(
    override var text: String,
    override var alignment: TextAlignment = TextAlignment.LEFT,
    override var fontSize: FontSize = FontSize.LARGE,
    var lineBreaksTop: Int = 0,
) : PrintableText(text, alignment, true, fontSize) {

    /**
     * Appends the heading to the printer output.
     *
     * Adds the specified number of line breaks before the heading.
     * Then prints the heading text with the specified alignment and font size (always in bold).
     * Finally, adds a single line break after the heading.
     *
     * @param devicePrinter The printer device to append the heading to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        if (lineBreaksTop > 0) {
            devicePrinter.appendMultipleSpace(lineBreaksTop)
        }
        devicePrinter.appendText(
            text = text,
            fontSize = fontSize.toBlazeFontSize(),
            alignment = alignment.toAlignMode(),
            isBoldFont = true,
        )
        devicePrinter.appendMultipleSpace(1)
    }
}
