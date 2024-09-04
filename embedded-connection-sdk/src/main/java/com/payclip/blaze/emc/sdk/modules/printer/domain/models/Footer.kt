package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable footer consisting of a divider followed by a list of text lines.
 *
 * @param texts The list of text lines to be included in the footer.
 */
class Footer(
    var texts: List<String>,
) : Printable() {

    /**
     * Appends the footer to the printer output.
     *
     * Adds a space, a divider, another space, and then prints each text line with center alignment and extra small font size.
     * Finally, adds spaces at the end.
     *
     * @param devicePrinter The printer device to append the footer to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        devicePrinter.appendSpace()
        Divider().appendComponent(devicePrinter)
        devicePrinter.appendSpace()
        texts.forEach {
            Section(
                text = it,
                alignment = TextAlignment.CENTER,
                fontSize = FontSize.EXTRA_SMALL,
                lineBreaks = 1,
            ).appendComponent(
                devicePrinter,
            )
        }
        devicePrinter.appendSpace()
    }
}
