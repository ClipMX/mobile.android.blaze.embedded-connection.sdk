package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable line break, adding vertical space to the printed output.
 *
 * @param spaces The number of line breaks to add. Default is 1.
 */
class LineBreak(
    var spaces: Int = 1,
) : Printable() {

    /**
     * Appends the line break to the printer output.
     *
     * Adds the specified number of line breaks (empty lines) to the output.
     *
     * @param devicePrinter The printer device to append the line break to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        devicePrinter.appendMultipleSpace(spaces)
    }
}
