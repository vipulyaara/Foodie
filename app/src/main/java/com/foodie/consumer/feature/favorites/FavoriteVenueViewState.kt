package com.foodie.consumer.feature.favorites

import com.foodie.consumer.feature.common.BaseViewState
import com.foodie.data.entities.FavoriteEntryWithVenue

/**
 * @author Vipul Kumar; dated 22/12/18.
 */
data class FavoriteVenueViewState(
    var favoriteVenues: List<FavoriteEntryWithVenue>? = arrayListOf(),
    var isLoading: Boolean = false
) : BaseViewState()
