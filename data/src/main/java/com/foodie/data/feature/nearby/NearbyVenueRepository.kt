package com.foodie.data.feature.nearby

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.extensions.parallelForEach
import com.foodie.data.feature.common.Repository
import com.foodie.data.feature.entry.VenueRepository
import com.foodie.data.feature.favorite.FavoriteVenueRepository
import com.foodie.data.model.Success
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Repository to fetch and update nearby venues.
 */
class NearbyVenueRepository : Repository() {
    private val localNearbyVenueStore: LocalNearbyVenueStore by kodeinInstance.instance()
    private val localVenueStore: LocalVenueStore by kodeinInstance.instance()
    private val entryDataSource: NearbyVenueDataSource by kodeinInstance.instance()
    private val venueRepository: VenueRepository by kodeinInstance.instance()
    private val favoriteVenueRepository: FavoriteVenueRepository by kodeinInstance.instance()

    fun observeForPaging() = localNearbyVenueStore.observeForPaging()

    fun observeForFlowable() =
        localNearbyVenueStore.observeForFlowable(10, 0)

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
                    entry.copy(
                        venueId = venueId,
                        ll = ll,
                        page = page
                    )
                }.also { entries ->
                    if (resetOnSave) {
                        localNearbyVenueStore.deleteAll()
                    }
                    logger.d("saving $entries")
                    localNearbyVenueStore.saveVenueEntries(page, entries)
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
