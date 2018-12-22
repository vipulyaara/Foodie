package com.foodie.data.feature.nearby

import androidx.paging.DataSource
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.data.db.DatabaseTransactionRunner
import com.foodie.data.data.db.daos.NearbyVenueEntryDao
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.entities.NearbyVenueEntry
import io.reactivex.Flowable
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Local store to perform database operations.
 */
class LocalNearbyVenueStore {
    private val transactionRunner: DatabaseTransactionRunner by kodeinInstance.instance()
    private val nearbyVenueEntryDao: NearbyVenueEntryDao by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    fun observeForFlowable(
        targetId: String,
        count: Int,
        offset: Int
    ): Flowable<List<NearbyEntryWithVenue>> {
        return nearbyVenueEntryDao.entriesFlowable(targetId, count, offset)
    }

    fun observeForPaging(ll: String): DataSource.Factory<Int, NearbyEntryWithVenue> =
        nearbyVenueEntryDao.entriesDataSource(ll)

    fun saveVenueEntries(page: Int, targetId: String, entries: List<NearbyVenueEntry>) =
        transactionRunner {
            val entriesSae = entries.distinctBy { it.venueId }
            nearbyVenueEntryDao.deletePage(page, targetId)
            nearbyVenueEntryDao.insertAll(entriesSae)
        }

    fun deleteAll(ll: String) = nearbyVenueEntryDao.deleteAll(ll)

    fun getLastPage(): Int? = nearbyVenueEntryDao.getLastPage()
}
