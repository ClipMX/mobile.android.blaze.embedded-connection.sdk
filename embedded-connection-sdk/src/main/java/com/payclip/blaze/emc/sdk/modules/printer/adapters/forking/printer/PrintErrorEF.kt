package com.payclip.blaze.emc.sdk.modules.printer.adapters.forking.printer

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.ClipPrinterError
import com.payclip.blaze.printer.core.domain.PrintError

fun PrintError.toClipPrinterError(): ClipPrinterError =
    when (this) {
        PrintError.PRINT_FAIL -> ClipPrinterError.PRINT_FAIL
        PrintError.ADD_STRING_FAIL -> ClipPrinterError.ADD_STRING_FAIL
        PrintError.ADD_IMAGE_FAIL -> ClipPrinterError.ADD_IMAGE_FAIL
        PrintError.PRINTER_BUSY -> ClipPrinterError.PRINTER_BUSY
        PrintError.PAPER_LACK -> ClipPrinterError.PAPER_LACK
        PrintError.WRONG_PACKAGE -> ClipPrinterError.WRONG_PACKAGE
        PrintError.PRINTER_FAULT -> ClipPrinterError.PRINTER_FAULT
        PrintError.PRINTER_TOO_HOT -> ClipPrinterError.PRINTER_TOO_HOT
        PrintError.PRINTER_UNFINISHED -> ClipPrinterError.PRINTER_UNFINISHED
        PrintError.NO_PRINTER_FOUND -> ClipPrinterError.NO_PRINTER_FOUND
        PrintError.UNKNOWN -> ClipPrinterError.UNKNOWN
    }
