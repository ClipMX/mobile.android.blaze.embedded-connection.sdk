package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import android.graphics.Bitmap
import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable image.
 *
 * @param bitmapResource The bitmap image to be printed.
 */
class PrintableImage(
    var bitmapResource: Bitmap,
) : Printable() {
    /**
     * Appends the image to the printer output.
     *
     * @param devicePrinter The printer device to append the image to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        devicePrinter.appendImage(bitmapResource)
    }
}
