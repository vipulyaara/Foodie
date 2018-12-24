package com.foodie.consumer.feature.detail

import com.airbnb.epoxy.TypedEpoxyController
import com.foodie.consumer.itemVenueDetail

/**
 * @author Vipul Kumar; dated 22/12/18.
 *
 * Controller to lay out items for [VenueDetailFragment].
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
