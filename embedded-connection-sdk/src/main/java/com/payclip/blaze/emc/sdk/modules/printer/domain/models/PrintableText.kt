package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize
import com.payclip.blaze.printer.core.domain.Printer

/**
 * An abstract class that represents printable text with formatting options.
 *
 * This class serves as a base for other printable text classes that require text, alignment, bold, and font size properties.
 *
 * @param text The text content to be printed.
 * @param alignment The text alignment for printing.
 * @param boldEnabled Indicates whether the text should be printed in bold.
 * @param fontSize The font size for printing the text.
 */
abstract class PrintableText(
    open var text: String,
    open var alignment: TextAlignment,
    open var boldEnabled: Boolean,
    open var fontSize: FontSize,
) : Printable()
