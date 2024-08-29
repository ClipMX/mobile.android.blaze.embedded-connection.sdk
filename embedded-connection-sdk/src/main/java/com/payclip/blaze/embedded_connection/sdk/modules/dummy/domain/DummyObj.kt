package com.payclip.blaze.embedded_connection.sdk.modules.dummy.domain

class DummyObj {

    fun getDummyString(): String {
        return "Dummy String from EMC SDK (Embedded Connection SDK)"
    }

    companion object {
        fun instance(): DummyObj {
            return DummyObj()
        }
    }
}