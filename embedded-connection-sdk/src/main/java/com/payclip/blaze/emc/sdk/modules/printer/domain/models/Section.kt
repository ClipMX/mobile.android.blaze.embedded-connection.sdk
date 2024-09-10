package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toAlignMode
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions.toBlazeFontSize
import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable text section with formatting options and optional line breaks.
 *
 * @param text The text content of the section.
 * @param lineBreaks The number of line breaks to add after the section. Default is 0.
 * @param alignment The text alignment for the section. Default is `TextAlignment.LEFT`.
 * @param fontSize The font size for the section. Default is `FontSize.MEDIUM`.
 * @param boldEnabled Indicates whether the text should be printed in bold. Default is false.
 */
class Section(
    override var text: String,
    var lineBreaks: Int = 0,
    override var alignment: TextAlignment = TextAlignment.LEFT,
    override var fontSize: FontSize = FontSize.MEDIUM,
    override var boldEnabled: Boolean = false,
) : PrintableText(text, alignment, boldEnabled, fontSize) {

    /**
     * Appends the section to the printer output.
     *
     * Prints the text content with the specified formatting (alignment, font size, and bold).
     * Then, adds the specified number of line breaks after the text.
     *
     * @param devicePrinter The printer device to append the section to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        devicePrinter.appendText(
            text = text,
            fontSize = fontSize.toBlazeFontSize(),
            alignment = alignment.toAlignMode(),
            isBoldFont = boldEnabled,
        )
        devicePrinter.appendMultipleSpace(lineBreaks)
    }
}
