package com.payclip.blaze.emc.sdk.modules.printer.domain.types

/**
 * Represents different error states that can occur during printing operations with a Clip Printer.
 *
 * Each enum value corresponds to a specific error condition:
 *
 * - **PRINT_FAIL:** General printing failure.
 * - **ADD_STRING_FAIL:** Failed to add a string to the print buffer.
 * - **ADD_IMAGE_FAIL:** Failed to add an image to the print buffer.
 * - **PRINTER_BUSY:** The printer is currently busy with another operation.
 * - **PAPER_LACK:** The printer is out of paper.
 * - **WRONG_PACKAGE:** Incorrect or incompatible printer driver or package.
 * - **PRINTER_FAULT:** A general printer fault has occurred.
 * - **PRINTER_TOO_HOT:** The printer has overheated.
 * - **PRINTER_UNFINISHED:** A previous printing operation was not completed.
 * - **NO_PRINTER_FOUND:** No compatible printer was found.
 * - **UNKNOWN:** An unknown error occurred.
 */
enum class ClipPrinterError {
    PRINT_FAIL,
    ADD_STRING_FAIL,
    ADD_IMAGE_FAIL,
    PRINTER_BUSY,
    PAPER_LACK,
    WRONG_PACKAGE,
    PRINTER_FAULT,
    PRINTER_TOO_HOT,
    PRINTER_UNFINISHED,
    NO_PRINTER_FOUND,
    UNKNOWN,
}
