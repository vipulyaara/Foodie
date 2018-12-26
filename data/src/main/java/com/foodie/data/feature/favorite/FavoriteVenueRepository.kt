package com.foodie.data.feature.favorite

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.entities.FavoriteVenueEntry
import com.foodie.data.feature.common.Repository
import org.kodein.di.generic.instance
import java.util.Random

class FavoriteVenueRepository : Repository() {
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val localStore: LocalFavoriteVenueStore by kodeinInstance.instance()

    fun observeFavoriteVenues() = localStore.observeFavoriteVenues()

    fun addToFavorites(venueId: String) {
        localStore.addToFavorites(FavoriteVenueEntry(id = Random().nextLong(), venueId = venueId))
    }

    fun removeFromFavorites(venueId: String) {
        localStore.removeFromFavorites(
            FavoriteVenueEntry(
                id = Random().nextLong(),
                venueId = venueId
            )
        )
    }
}
