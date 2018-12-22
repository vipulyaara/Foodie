package com.foodie.data.feature.nearby

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.db.DatabaseTransactionRunner
import com.foodie.data.data.db.EntityInserter
import com.foodie.data.data.db.daos.VenueDao
import com.foodie.data.model.Venue
import io.reactivex.Flowable
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Local store to perform database operations for venues.
 */
class LocalVenueStore {
    private val entityInserter: EntityInserter by kodeinInstance.instance()
    private val venueDao: VenueDao by kodeinInstance.instance()
    private val transactionRunner: DatabaseTransactionRunner by kodeinInstance.instance()

    fun getVenue(venueId: String) = venueDao.getVenueWithId(venueId)

    fun observeVenue(venueId: String): Flowable<Venue> = venueDao.getVenueWithIdFlowable(venueId)

    fun saveVenue(venue: Venue) = entityInserter.insertOrUpdate(venueDao, venue)

    fun getIdOrSavePlaceholder(venue: Venue): String = transactionRunner {
        venueDao.insert(venue)
        venue.venueId
    }
}
