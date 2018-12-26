package com.foodie.data.feature.block

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.db.daos.BlockedVenueEntryDao
import com.foodie.data.entities.BlockedEntryWithVenue
import com.foodie.data.entities.BlockedVenueEntry
import com.foodie.data.feature.common.LocalStore
import io.reactivex.Flowable
import org.kodein.di.generic.instance

class LocalBlockedVenueStore : LocalStore() {
    private val dao: BlockedVenueEntryDao by kodeinInstance.instance()

    fun getBlockedVenuesFlowable(): Flowable<List<BlockedEntryWithVenue>> =
        dao.getBlockedVenuesFlowable()

    fun addToBlockedVenues(blockedVenueEntry: BlockedVenueEntry) {
        dao.insert(blockedVenueEntry)
    }

    fun removeFromBlockedVenues(venueId: String) {
        dao.delete(venueId)
    }
}
