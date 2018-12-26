package com.foodie.data.feature.favorite

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.db.daos.FavoriteVenueEntryDao
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.entities.FavoriteVenueEntry
import com.foodie.data.feature.common.LocalStore
import io.reactivex.Flowable
import org.kodein.di.generic.instance

class LocalFavoriteVenueStore : LocalStore() {
    private val dao: FavoriteVenueEntryDao by kodeinInstance.instance()

    fun observeFavoriteVenues(): Flowable<List<FavoriteEntryWithVenue>> =
        dao.getFavoriteVenuesFlowable()

    fun getFavoriteVenues(): List<FavoriteEntryWithVenue> =
        dao.getFavoriteVenues()


    fun addToFavorites(favoriteVenueEntry: FavoriteVenueEntry) {
        dao.insert(favoriteVenueEntry)
    }

    fun removeFromFavorites(venueId: String) {
        dao.delete(venueId)
    }
}
