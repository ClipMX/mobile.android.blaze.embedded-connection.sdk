package com.payclip.blaze.emc.sdk.modules.printer.domain.exceptions

import com.payclip.blaze.emc.sdk.modules.printer.domain.models.ItemList

class ItemListContentException(
    itemList: ItemList,
) : Exception(
        "You can defined only text or rowContent in ItemList \ntext: ${itemList.text} \nrowContent: ${itemList.rowContent?.textColumn1}-------${itemList.rowContent?.textColumn2}",
    )
