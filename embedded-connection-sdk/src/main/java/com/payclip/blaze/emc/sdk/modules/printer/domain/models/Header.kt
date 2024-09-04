package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import android.graphics.Bitmap
import com.payclip.blaze.printer.core.domain.AlignMode
import com.payclip.blaze.printer.core.domain.FontSize
import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable header that can include a title and an optional image.
 *
 * @param title The title text for the header.
 * @param image An optional bitmap image to be included in the header. Default is null.
 */
class Header(
    var title: String,
    var image: Bitmap? = null,
) : Printable() {

    /**
     * Appends the header to the printer output.
     *
     * If an image is provided, it's printed first.
     * Then, the title is printed with specific formatting based on its length:
     * - If the title exceeds `TITLE_LENGTH`, it's printed left-aligned with a large font size.
     * - Otherwise, it's printed center-aligned with an extra large font size.
     *
     * A space is added after the header content.
     *
     * @param devicePrinter The printer device to append the header to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        image?.let {
            devicePrinter.appendImage(it)
        }
        if (title.length > TITLE_LENGTH) {
            devicePrinter.appendText(
                text = title.take(TITLE_EXTRA_LENGTH),
                fontSize = FontSize.LARGE,
                alignment = AlignMode.LEFT,
                isBoldFont = true,
            )
        } else {
            devicePrinter.appendText(
                text = title.take(TITLE_LENGTH),
                fontSize = FontSize.EXTRA_LARGE,
                alignment = AlignMode.CENTER,
                isBoldFont = true,
            )
        }

        devicePrinter.appendSpace()
    }

    companion object {
        const val TITLE_LENGTH = 18
        const val TITLE_EXTRA_LENGTH = 24
    }
}
