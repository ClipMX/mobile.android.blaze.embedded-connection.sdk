package com.payclip.blaze.emc.sdk.modules.printer.domain.models

/**
 * Represents the content of a two-column row for printing.
 *
 * @param textColumn1 The text content for the first column.
 * @param textColumn2 The text content for the second column.
 * @param isListHeader Indicates whether this row is a header for a list. Default is false.
 */
class RowContent(
    var textColumn1: String,
    var textColumn2: String,
    var isListHeader: Boolean = false,
)
