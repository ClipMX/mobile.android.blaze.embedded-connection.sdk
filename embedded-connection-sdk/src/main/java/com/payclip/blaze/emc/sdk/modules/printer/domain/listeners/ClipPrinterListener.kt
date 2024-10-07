package com.payclip.blaze.emc.sdk.modules.printer.domain.listeners

import com.payclip.blaze.emc.sdk.modules.printer.domain.types.ClipPrinterError

/**
 * A listener interface for receiving events related to clip printing.
 *
 * Implement this interface to be notified of the success or failure of a clip printing operation.
 */
interface ClipPrinterListener {

    /**
     * Called when a clip is successfully printed.
     */
    fun onSuccessfulPrint()

    /**
     * Called when a clip printing operation fails.
     *
     * @param clipPrinterError The error that occurred during the printing operation.
     */
    fun onFailedPrint(clipPrinterError: ClipPrinterError)
}
