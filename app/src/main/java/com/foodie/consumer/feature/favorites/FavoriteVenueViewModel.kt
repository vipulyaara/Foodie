package com.foodie.consumer.feature.favorites

import com.foodie.consumer.feature.common.BaseViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.feature.favorite.AddToFavoriteVenues
import com.foodie.data.feature.favorite.RemoveFromFavoriteVenues
import com.foodie.data.feature.favorite.UpdateFavoriteVenues
import com.foodie.data.feature.launchInteractor
import io.reactivex.rxkotlin.plusAssign
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/12/18.
 *
 * ViewModel for venue details.
 */
class FavoriteVenueViewModel : BaseViewModel<FavoriteVenueViewState>(FavoriteVenueViewState()) {
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val updateFavoriteVenues: UpdateFavoriteVenues by kodeinInstance.instance()
    private val addToFavoriteVenues: AddToFavoriteVenues by kodeinInstance.instance()
    private val removeFromFavoriteVenues: RemoveFromFavoriteVenues by kodeinInstance.instance()

    init {
        disposables += updateFavoriteVenues.observe()
            .toObservable()
            .subscribeOn(schedulers.io)
            .execute { favorites ->
                copy(favoriteVenues = favorites)
            }
    }

    fun updateFavorites() {
        scope.launchInteractor(updateFavoriteVenues, UpdateFavoriteVenues.ExecuteParams(Unit))
    }

    fun addToFavorites(venueId: String) {
        scope.launchInteractor(addToFavoriteVenues, AddToFavoriteVenues.Param(venueId))
    }

    fun removeFromFavorites(venueId: String) {
        scope.launchInteractor(removeFromFavoriteVenues, RemoveFromFavoriteVenues.Param(venueId))
    }
}
