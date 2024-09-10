package com.payclip.blaze.emc.sdk.modules.printer.domain.listeners

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.ClipPrinterError

interface ClipPrinterListener {
    fun onSuccessfulPrint()

    fun onFailedPrint(clipPrinterError: ClipPrinterError)
}
