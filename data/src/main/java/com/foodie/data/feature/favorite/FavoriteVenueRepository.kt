package com.foodie.data.feature.favorite

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.entities.FavoriteVenueEntry
import com.foodie.data.feature.entry.VenueRepository
import com.foodie.data.feature.nearby.LocalVenueStore
import org.kodein.di.generic.instance

class FavoriteVenueRepository {
    private val localFavoriteVenueStore: LocalFavoriteVenueStore by kodeinInstance.instance()
    private val localVenueStore: LocalVenueStore by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()
    private val venueRepository: VenueRepository by kodeinInstance.instance()

    fun observeForPaging() = localFavoriteVenueStore.observeForPaging()

    fun observeForFlowable() =
        localFavoriteVenueStore.observeForFlowable(50, 0)

    suspend fun loadNextPage() {
        val lastPage = localFavoriteVenueStore.getLastPage()
        if (lastPage != null)
            updateVenues(lastPage + 1, false)
        else
            refresh()
    }

    suspend fun refresh() {
        updateVenues(0, false)
    }

    fun addToFavorites(venueId: String) {
        localFavoriteVenueStore.addToFavorites(
            FavoriteVenueEntry(
                venueId = venueId,
                page = 0,
                pageOrder = 0
            )
        )
    }

    fun removeFromFavorites(venueId: String) {
        localFavoriteVenueStore.removeFromFavorites(
            FavoriteVenueEntry(
                venueId = venueId,
                page = 0,
                pageOrder = 0
            )
        )
    }

    private suspend fun updateVenues(
        page: Int,
        resetOnSave: Boolean
    ) {
    }
}
