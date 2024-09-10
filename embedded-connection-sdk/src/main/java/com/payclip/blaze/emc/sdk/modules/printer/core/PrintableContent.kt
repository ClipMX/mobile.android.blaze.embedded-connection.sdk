package com.payclip.blaze.emc.sdk.modules.printer.core

import android.graphics.Bitmap
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Footer
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Header
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Printable

/**
 * Represents the content to be printed, including an optional header, a list of printable items, and an optional footer.
 *
 * Use the `Builder` class to create an instance of `PrintableContent`.
 */
class PrintableContent private constructor(
    /**
     * The header of the content. Can be null.
     */
    val header: Header?,
    /**
     * The list of printable items.
     */
    val printableList: List<Printable>,
    /**
     * The footer of the content. Can be null.
     */
    val footer: Footer?,
) {

    /**
     * Builder class for creating a `PrintableContent` instance.
     */
    class Builder {
        private var header: Header? = null
        private var printableList: MutableList<Printable> = mutableListOf()
        private var footer: Footer? = null

        /**
         * Sets the header for the printable content.
         *
         * @param title The title of the header.
         * @param image An optional bitmap image to include in the header.
         * @return The builder instance.
         */
        fun setHeader(
            title: String,
            image: Bitmap? = null,
        ): Builder {
            this.header = Header(title, image)
            return this
        }

        /**
         * Adds a printable item to the list.
         *
         * @param printable The printable item to add.
         * @return The builder instance.
         */
        fun addPrintable(printable: Printable): Builder {
            printableList.add(printable)
            return this
        }

        /**
         * Sets the footer for the printable content.
         *
         * @param texts A list of strings to include in the footer.
         * @return The builder instance.
         */
        fun setFooter(texts: List<String>): Builder {
            this.footer = Footer(texts)
            return this
        }

        /**
         * Creates a `PrintableContent` instance using the configured values.
         *
         * @return A `PrintableContent` instance.
         */
        fun build(): PrintableContent = PrintableContent(header, printableList, footer)
    }
}
