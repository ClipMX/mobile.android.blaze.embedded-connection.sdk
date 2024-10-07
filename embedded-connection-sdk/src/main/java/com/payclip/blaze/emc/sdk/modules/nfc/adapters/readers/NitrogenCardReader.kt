package com.payclip.blaze.emc.sdk.modules.nfc.adapters.readers

import android.content.Context
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.SdkResult
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener

class NitrogenCardReader(context: Context) : OnCardInfoListener {
    private lateinit var cardReader: com.nexgo.oaf.apiv3.device.reader.CardReader
    private val TIMEOUT_IN_SECONDS = 60
    private val SUPPORTED_SLOT_TYPES = HashSet<CardSlotTypeEnum>().apply {
        add(CardSlotTypeEnum.SWIPE)
        add(CardSlotTypeEnum.ICC1)
        add(CardSlotTypeEnum.RF)
    }

    init {
        val deviceEngine: DeviceEngine = APIProxy.getDeviceEngine(context)
        cardReader = deviceEngine.cardReader
    }

    fun startReadCard() {
        cardReader.searchCard(SUPPORTED_SLOT_TYPES, TIMEOUT_IN_SECONDS, this)
    }

    fun stopReadCard() {
        cardReader.stopSearch()
    }

    override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity?) {
        var foo = ""
        if (retCode == SdkResult.Success && cardInfo != null) {
            when (cardInfo.cardExistslot) {
                CardSlotTypeEnum.SWIPE -> {
                    foo = "is card swipe"
                }

                CardSlotTypeEnum.ICC1 -> {
                    foo = "is card ICC1"
                }

                CardSlotTypeEnum.RF -> {
                    foo = "is card RF"
                }

                else -> {
                    foo = "not supported"
                }
            }
        }
    }

    override fun onSwipeIncorrect() {
        TODO("Not yet implemented")
    }

    override fun onMultipleCards() {
        TODO("Not yet implemented")
    }
}