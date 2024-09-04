package com.payclip.blaze.emc.sdk.modules.printer.domain.types.extensionFunctions

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.TextAlignment
import com.payclip.blaze.printer.core.domain.AlignMode

/**
 * Converts a `TextAlignment` enum value to its corresponding `AlignMode` value.
 *
 * This extension function provides a mapping between the two alignment enums, likely used for compatibility between different modules or libraries.
 *
 * @return The equivalent `AlignMode` value.
 */
fun TextAlignment.toAlignMode(): AlignMode =
    when (this) {
        TextAlignment.LEFT -> AlignMode.LEFT
        TextAlignment.CENTER -> AlignMode.CENTER
        TextAlignment.RIGHT -> AlignMode.RIGHT
    }
