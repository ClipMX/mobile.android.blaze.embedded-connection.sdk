package com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.FontSize

/**
 * Converts a `FontSize` enum value to its corresponding `com.payclip.blaze.printer.core.domain.FontSize` value.
 *
 * This extension function provides a mapping between the two `FontSize` enums, likely used for compatibility between different modules or libraries.
 *
 * @return The equivalent `com.payclip.blaze.printer.core.domain.FontSize` value.
 */
fun FontSize.toBlazeFontSize(): com.payclip.blaze.printer.core.domain.FontSize =
    when (this) {
        FontSize.EXTRA_SMALL -> com.payclip.blaze.printer.core.domain.FontSize.EXTRA_SMALL
        FontSize.SMALL -> com.payclip.blaze.printer.core.domain.FontSize.SMALL
        FontSize.MEDIUM -> com.payclip.blaze.printer.core.domain.FontSize.MEDIUM
        FontSize.LARGE -> com.payclip.blaze.printer.core.domain.FontSize.LARGE
        FontSize.EXTRA_LARGE -> com.payclip.blaze.printer.core.domain.FontSize.EXTRA_LARGE
        FontSize.SUPER_LARGE -> com.payclip.blaze.printer.core.domain.FontSize.SUPER_LARGE
    }
