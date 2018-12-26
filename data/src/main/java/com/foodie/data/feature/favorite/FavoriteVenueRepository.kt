package com.foodie.data.feature.favorite

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.entities.FavoriteVenueEntry
import com.foodie.data.feature.common.Repository
import org.kodein.di.generic.instance

class FavoriteVenueRepository : Repository() {
    private val localStore: LocalFavoriteVenueStore by kodeinInstance.instance()

    fun observeFavoriteVenues() = localStore.observeFavoriteVenues()

    fun addToFavorites(venueId: String) {
        localStore.addToFavorites(FavoriteVenueEntry(venueId = venueId))
    }

    fun removeFromFavorites(venueId: String) {
        localStore.removeFromFavorites(venueId = venueId)
    }
}
