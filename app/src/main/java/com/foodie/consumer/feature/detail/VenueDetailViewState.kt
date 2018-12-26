package com.foodie.consumer.feature.detail

import com.foodie.consumer.feature.common.BaseViewState
import com.foodie.data.entities.BlockedEntryWithVenue
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.entities.VenueDetail

/**
 * @author Vipul Kumar; dated 22/12/18.
 */
data class VenueDetailViewState(
    var isFavorite: Boolean = false,
    var isBlocked: Boolean = false,
    var venueDetail: VenueDetail = VenueDetail(),
    var favoriteVenues: List<FavoriteEntryWithVenue> = arrayListOf(),
    var blockedVenues: List<BlockedEntryWithVenue> = arrayListOf(),
    var isLoading: Boolean = true
) : BaseViewState() {
    companion object {
        fun default() = VenueDetailViewState()
    }
}
