package com.foodie.data.feature.favorite

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.feature.Interactor
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.generic.instance

/**
 * Interactor for adding favorite venues.
 * */
class AddToFavoriteVenues :
    Interactor<AddToFavoriteVenues.Param> {
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val favoriteVenueRepository: FavoriteVenueRepository by kodeinInstance.instance()

    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override suspend fun invoke(executeParams: Param) {
        favoriteVenueRepository.addToFavorites(executeParams.venueId)
    }

    data class Param(val venueId: String)
}
