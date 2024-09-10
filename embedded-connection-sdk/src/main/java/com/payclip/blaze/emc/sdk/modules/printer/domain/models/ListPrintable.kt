package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.printer.core.domain.Printer

/**
 * Represents a printable list of items, where each item can be a single line of text or a two-column row.
 *
 * Items can be optionally prefixed with a bullet point and separated by a divider.
 *
 * @param items The list of `ItemList` objects to be printed.
 * @param bulletSpan The character used for the bullet point. Default is '*'.
 * @param divider An optional `Divider` to be printed after each item. Default is null.
 */
class ListPrintable(
    var items: List<ItemList>,
    var bulletSpan: Char = '*',
    var divider: Divider? = null,
) : Printable() {
    /**
     * Appends the list of items to the printer output.
     *
     * Iterates through the `items` list and prints each item.
     * If an item is a single line of text, it's prefixed with the `bulletSpan`.
     * A divider is appended after each item unless it's a two-column row and not a list header.
     *
     * @param devicePrinter The printer device to append the list to.
     */
    override fun appendComponent(devicePrinter: Printer) {
        items.forEach { item ->
            val itemCustom =
                if (item.text.isNotBlank()) ItemList("$bulletSpan ${item.text}") else item
            itemCustom.appendComponent(devicePrinter)
            if (item.rowContent?.isListHeader == false || item.text.isNotBlank()) {
                divider?.appendComponent(devicePrinter)
            }
        }
    }
}
