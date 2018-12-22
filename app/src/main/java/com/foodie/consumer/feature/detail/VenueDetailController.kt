package com.foodie.consumer.feature.detail

import com.airbnb.epoxy.TypedEpoxyController
import com.foodie.consumer.itemVenueDetail

/**
 * @author Vipul Kumar; dated 22/12/18.
 */
class VenueDetailController : TypedEpoxyController<VenueDetailViewState>() {
    override fun buildModels(data: VenueDetailViewState?) {
        data?.venueDetail?.let {
            itemVenueDetail {
                id(it.venueId)
                venue(it)
            }
        }
    }
}
