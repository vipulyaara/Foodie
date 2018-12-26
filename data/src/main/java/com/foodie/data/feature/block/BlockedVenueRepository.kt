package com.foodie.data.feature.block

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.db.daos.BlockedVenueEntryDao
import com.foodie.data.entities.BlockedVenueEntry
import com.foodie.data.feature.common.Repository
import org.kodein.di.generic.instance
import java.util.Random

class BlockedVenueRepository : Repository() {
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val dao: BlockedVenueEntryDao by kodeinInstance.instance()

    fun observeBlockedVenues() = dao

    fun addToBlockedVenues(venueId: String) {
        dao.insert(BlockedVenueEntry(id = Random().nextLong(), venueId = venueId))
    }

    fun removeFromBlockedVenues(venueId: String) {
        dao.delete(BlockedVenueEntry(id = Random().nextLong(), venueId = venueId))
    }
}
