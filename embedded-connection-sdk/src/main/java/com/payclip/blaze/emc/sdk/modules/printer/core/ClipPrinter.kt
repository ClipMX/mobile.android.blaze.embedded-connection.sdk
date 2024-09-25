package com.payclip.blaze.emc.sdk.modules.printer.core

import android.content.Context
import android.util.Log
import com.payclip.blaze.commons.device.Device
import com.payclip.blaze.commons.device.DeviceService
import com.payclip.blaze.commons_hardware.printer.EmbeddedPrinterBuilder
import com.payclip.blaze.commons_hardware.shared.ClipReaderType
import com.payclip.blaze.commons_hardware.shared.ReaderTypeEnum
import com.payclip.blaze.emc.sdk.modules.printer.adapters.forking.printer.toClipPrinterError
import com.payclip.blaze.emc.sdk.modules.printer.domain.exceptions.ClipPrinterNotBuiltException
import com.payclip.blaze.emc.sdk.modules.printer.domain.listeners.ClipPrinterListener
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Footer
import com.payclip.blaze.emc.sdk.modules.printer.domain.models.Header
import com.payclip.blaze.emc.sdk.shared.domain.exceptions.ContextNotInitializedException
import com.payclip.blaze.emc.sdk.shared.domain.utils.setupReaderDevice
import com.payclip.blaze.printer.core.domain.PrintResult
import com.payclip.blaze.printer.core.domain.Printer

/**
 * A class responsible for printing content to a Clip reader device with an embedded printer.
 *
 * This class uses the builder pattern for instantiation. You must provide a `Context`
 * during the building process.
 *
 * @throws ClipPrinterNotBuiltException if no Clip reader with a printer is detected or if the printer cannot be built.
 * @throws ContextNotInitializedException if the provided context is null.
 */
class ClipPrinter private constructor(
    context: Context?,
) {
    private lateinit var devicePrinter: Printer

    init {
        if (ClipReaderType.get() == ReaderTypeEnum.NO_READER) {
            throw ClipPrinterNotBuiltException()
        }
        if (context == null) {
            throw ContextNotInitializedException()
        } else {
            EmbeddedPrinterBuilder().build(context).let { printer ->
                printer?.let {
                    devicePrinter = it
                } ?: run {
                    throw ClipPrinterNotBuiltException()
                }
            }
        }
    }

    /**
     * Prints the provided `PrintableContent` to the printer.
     *
     * Prints the header, then each item in the `printableList`, and finally the footer (if available).
     *
     * @param printableContent The content to be printed.
     */
    fun print(
        printableContent: PrintableContent,
        clipPrinterListener: ClipPrinterListener,
    ) {
        printableContent.header?.let { header: Header ->
            header.appendComponent(devicePrinter)
        }
        printableContent.printableList.forEach { itemPrintable ->
            itemPrintable.appendComponent(devicePrinter)
        }
        printableContent.footer?.let { footer: Footer ->
            footer.appendComponent(devicePrinter)
        }
        if(DeviceService.device == Device.TOTAL2){
            devicePrinter.appendMultipleSpace(4)
        }
        devicePrinter.print { printResult ->
            when (printResult) {
                is PrintResult.Success -> {
                    clipPrinterListener.onSuccessfulPrint()
                }

                is PrintResult.Failure -> {
                    clipPrinterListener.onFailedPrint(printResult.error.toClipPrinterError())
                }
            }
            Log.i("ClipPrinter", "Result: $printResult")
        }
    }

    /**
     * Builder class for creating a `ClipPrinter` instance.
     */
    class Builder {
        private var context: Context? = null

        /**
         * Sets the Android context required for building the printer.
         *
         * @param context The Android context.
         * @return The builder instance.
         */
        fun setContext(context: Context): Builder {
            this.context = context
            return this
        }

        /**
         * Builds a `ClipPrinter` instance.
         *
         * Sets up the reader device and creates a `ClipPrinter` using the provided context.
         *
         * @return A `ClipPrinter` instance.
         */
        fun build(): ClipPrinter {
            setupReaderDevice()
            return ClipPrinter(context)
        }
    }
}
