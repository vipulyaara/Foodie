package com.foodie.data.feature.nearby

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.feature.entry.VenueRepository
import com.foodie.data.model.Venue
import io.reactivex.Flowable
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Implementation of [VenueRepository].
 */
class VenueVenueRepository : VenueRepository {
    private val localVenueStore: LocalVenueStore by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    override suspend fun getVenue(entryId: String): Venue {
        if (needsUpdate(entryId)) {
            updateVenue(entryId)
        }
        return localVenueStore.getVenue(entryId)!!
    }

    override suspend fun updateVenue(entryId: String) {
        val localShow = localVenueStore.getVenue(entryId) ?: Venue()
        localVenueStore.saveVenue(localShow)
        logger.d("")
    }

    override fun needsUpdate(entryId: String): Boolean {
        return true
    }

    override fun observeVenue(entryId: String): Flowable<Venue> =
        localVenueStore.observeVenue(entryId)
}
