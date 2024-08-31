package com.payclip.blaze.emc.sdk.modules.printer.domain

import android.content.Context
import android.util.Log
import com.payclip.blaze.commons.device.Device
import com.payclip.blaze.commons.device.DeviceService
import com.payclip.blaze.commons_hardware.printer.EmbeddedPrinterBuilder
import com.payclip.blaze.commons_hardware.shared.ClipReaderType
import com.payclip.blaze.commons_hardware.shared.ReaderTypeEnum
import com.payclip.blaze.emc.sdk.modules.printer.domain.exceptions.ClipPrinterNotBuiltException
import com.payclip.blaze.emc.sdk.shared.domain.exceptions.ContextNotInitializedException
import com.payclip.blaze.printer.core.domain.AlignMode
import com.payclip.blaze.printer.core.domain.FontSize
import com.payclip.blaze.printer.core.domain.PrintResult
import com.payclip.blaze.printer.core.domain.Printer

class ClipPrinter internal constructor(
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

    fun check(): String {
        val foo = ClipReaderType.get()
        Log.i("ClipPrinter", "check: $foo")
        return foo.toString()
    }

    fun print(text: String) {
        devicePrinter.appendText(
            text = text,
            fontSize = FontSize.MEDIUM,
            alignment = AlignMode.LEFT,
            isBoldFont = false,
        )
        devicePrinter.print(
            object : (PrintResult) -> Unit {
                override fun invoke(printResult: PrintResult) {
                    Log.i("ClipPrinter", "Result: $printResult")
                }
            },
        )
    }

    class Builder {
        private var context: Context? = null

        fun setContext(context: Context): Builder {
            this.context = context
            return this
        }

        fun build(): ClipPrinter {
            when (DeviceService.device) {
                Device.TOTAL, Device.N80 -> ClipReaderType.save(ReaderTypeEnum.NITROGEN)
                Device.PRO2,
                Device.TOTAL2,
                -> ClipReaderType.save(ReaderTypeEnum.SODIUM)

                Device.PRO, Device.STAND -> ClipReaderType.save(ReaderTypeEnum.BORON)
                else -> ClipReaderType.save(ReaderTypeEnum.NO_READER)
            }
            return ClipPrinter(context)
        }
    }
}
