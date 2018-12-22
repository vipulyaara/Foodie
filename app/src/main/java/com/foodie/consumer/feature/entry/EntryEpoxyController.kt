package com.foodie.consumer.feature.entry

import com.airbnb.epoxy.paging.PagingEpoxyController
import com.foodie.data.entities.NearbyEntryWithVenue

open class EntryEpoxyController :
    PagingEpoxyController<NearbyEntryWithVenue>() {
    internal var callbacks: Callbacks<NearbyEntryWithVenue>? = null

    var isLoading = false
        set(value) {
            if (value != field) {
                field = value
                requestModelBuild()
            }
        }

    interface Callbacks<in LI> {
        fun onItemClicked(item: LI)
    }

    override fun buildModels(items: MutableList<NearbyEntryWithVenue>) {
    }
}
