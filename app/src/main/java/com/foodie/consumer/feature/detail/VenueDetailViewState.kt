package com.foodie.consumer.feature.detail

import com.foodie.consumer.feature.common.BaseViewState
import com.foodie.data.entities.VenueDetail

/**
 * @author Vipul Kumar; dated 22/12/18.
 */
data class VenueDetailViewState(
    val venueDetail: VenueDetail,
    val isLoading: Boolean
) : BaseViewState()
