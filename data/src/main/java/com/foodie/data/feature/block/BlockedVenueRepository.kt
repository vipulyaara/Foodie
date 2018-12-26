package com.foodie.data.feature.block

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.entities.BlockedVenueEntry
import com.foodie.data.feature.common.Repository
import org.kodein.di.generic.instance

class BlockedVenueRepository : Repository() {
    private val localStore: LocalBlockedVenueStore by kodeinInstance.instance()

    fun observeBlockedVenues() = localStore.getBlockedVenuesFlowable()

    fun addToBlockedVenues(venueId: String) {
        localStore.addToBlockedVenues(BlockedVenueEntry(venueId = venueId))
    }

    fun removeFromBlockedVenues(venueId: String) {
        localStore.removeFromBlockedVenues(venueId = venueId)
    }
}
