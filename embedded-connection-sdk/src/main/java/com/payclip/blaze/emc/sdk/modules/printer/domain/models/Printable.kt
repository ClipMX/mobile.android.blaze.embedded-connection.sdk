package com.payclip.blaze.emc.sdk.modules.printer.domain.models

import com.payclip.blaze.printer.core.domain.Printer

/**
 * An abstract class that defines the basic structure for printable components.
 *
 *  Classes that inherit from `Printable` represent individual elements that can be part of a printed document.
 */
abstract class Printable {
    /**
     * Appends the component's content to the printer output.
     *
     *  Each concrete subclass of `Printable` must implement this method to define how its content is added to the printed document.
     *
     * @param devicePrinter The printer device to append the content to.
     */
    abstract fun appendComponent(devicePrinter: Printer)
}
