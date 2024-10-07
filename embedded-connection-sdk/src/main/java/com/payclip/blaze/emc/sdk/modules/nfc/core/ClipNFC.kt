package com.payclip.blaze.emc.sdk.modules.nfc.core

import android.content.Context
import android.util.Log
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.card.ultralight.UltralightCCardHandler
import com.payclip.blaze.emc.sdk.modules.nfc.adapters.readers.NitrogenCardReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ClipNFC private constructor(
    context: Context,
    private var ultralightCCardHandler: UltralightCCardHandler,
    // private val cardReader: CardReader,

) {
    var stopRead = false
    private var nitrogenCardReader: NitrogenCardReader = NitrogenCardReader(context)

    fun read(): String {
        nitrogenCardReader.startReadCard()
        return "HOLA"
    }


    fun stopRead() {
        nitrogenCardReader.stopReadCard()
        stopRead = true
    }

    private fun startTimerLoop(delayMillis: Long, repeatCount: Int = 45, action: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(repeatCount) {
                delay(delayMillis)

                Log.i("ClipNFC", "Is Active: $isActive")
                if (!isActive) return@launch

                if (stopRead) {
                    stopRead = false
                    Log.i("ClipNFC", "Loop stopped")
                    this.cancel()
                    return@launch
                } else {
                    Log.i("ClipNFC", "execute action")
                    action()
                }
            }
        }
    }

    class Builder {
        lateinit var context: Context

        fun setContext(context: Context): Builder {
            this.context = context
            return this
        }

        fun build(): ClipNFC {
            if (this::context.isInitialized) {
                val deviceEngine = APIProxy.getDeviceEngine(context)
                val ultralightCCardHandler = deviceEngine.ultralightCCardHandler
                return ClipNFC(context, ultralightCCardHandler)
            } else {
                throw Exception("Context not initialized")
            }
        }
    }
}
