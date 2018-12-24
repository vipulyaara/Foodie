package com.foodie.consumer.feature.entry

import com.airbnb.epoxy.paging.PagingEpoxyController
import com.foodie.data.entities.Entry
import com.foodie.data.entities.EntryWithVenue

open class EntryEpoxyController<LI : EntryWithVenue<out Entry>> :
    PagingEpoxyController<LI>() {
    internal var callbacks: Callbacks<LI>? = null

    var isLoading = false
        set(value) {
            if (value != field) {
                field = value
                requestModelBuild()
            }
        }

    interface Callbacks<in LI> {
        fun onItemClicked(viewHolderId: Long, item: LI)
        fun onItemFavorited(item: LI)
    }

    override fun buildModels(items: MutableList<LI>) {
    }
}
