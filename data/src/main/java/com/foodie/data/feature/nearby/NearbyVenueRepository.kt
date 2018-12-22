package com.foodie.data.feature.nearby

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.extensions.parallelForEach
import com.foodie.data.feature.entry.VenueRepository
import com.foodie.data.model.Success
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Repository to fetch and update nearby venues.
 */
class NearbyVenueRepository {
    private val localNearbyVenueStore: LocalNearbyVenueStore by kodeinInstance.instance()
    private val localVenueStore: LocalVenueStore by kodeinInstance.instance()
    private val entryDataSource: NearbyVenueDataSource by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()
    private val venueRepository: VenueRepository by kodeinInstance.instance()

    fun observeForPaging(ll: String) = localNearbyVenueStore.observeForPaging(ll)

    fun observeForFlowable(ll: String) =
        localNearbyVenueStore.observeForFlowable(ll, 10, 0)

    suspend fun loadNextPage(ll: String) {
        val lastPage = localNearbyVenueStore.getLastPage()
        if (lastPage != null)
            updateVenues(ll, lastPage + 1, false)
        else
            refresh(ll)
    }

    suspend fun refresh(ll: String) {
        updateVenues(ll, 0, false)
    }

    private suspend fun updateVenues(
        ll: String,
        page: Int,
        resetOnSave: Boolean
    ) {
        val response = entryDataSource.getVenues(ll, page)
        logger.d("response $response")
        when (response) {
            is Success -> {
                response.data.map { (venue, entry) ->
                    val venueId = localVenueStore.getIdOrSavePlaceholder(venue)
                    entry.copy(venueId = venueId, ll = ll, page = page)
                }.also { entries ->
                    if (resetOnSave) {
                        localNearbyVenueStore.deleteAll(ll)
                    }
                    logger.d("saving $entries")
                    localNearbyVenueStore.saveVenueEntries(page, ll, entries)
                    entries.parallelForEach { entry ->
                        if (venueRepository.needsUpdate(entry.venueId))
                            venueRepository.updateVenue(entry.venueId)
                    }
                }
            }
            else -> {
                logger.e("API Error $response")
            }
        }
    }
}
