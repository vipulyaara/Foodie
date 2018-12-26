package com.foodie.consumer.feature.detail

import com.foodie.consumer.config.RxLoadingCounter
import com.foodie.consumer.feature.common.BaseViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.feature.block.AddToBlockedVenues
import com.foodie.data.feature.block.RemoveFromBlockedVenues
import com.foodie.data.feature.block.UpdateBlockedVenues
import com.foodie.data.feature.detail.UpdateVenueDetail
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
class VenueDetailViewModel : BaseViewModel<VenueDetailViewState>(VenueDetailViewState.default()) {
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val updateVenueDetail: UpdateVenueDetail by kodeinInstance.instance()
    private val updateFavoriteVenues: UpdateFavoriteVenues by kodeinInstance.instance()
    private val updateBlockedVenues: UpdateBlockedVenues by kodeinInstance.instance()
    private val addToFavoriteVenues: AddToFavoriteVenues by kodeinInstance.instance()
    private val addToBlockedVenues: AddToBlockedVenues by kodeinInstance.instance()
    private val removeFromFavoriteVenues: RemoveFromFavoriteVenues by kodeinInstance.instance()
    private val removeFromBlockedVenues: RemoveFromBlockedVenues by kodeinInstance.instance()
    private var venueId: String = ""

    private val loadingState = RxLoadingCounter()

    init {
        disposables += updateVenueDetail.observe()
            .toObservable()
            .subscribeOn(schedulers.io)
            .execute { copy(venueDetail = it) }

        disposables += updateFavoriteVenues.observe()
            .toObservable()
            .subscribeOn(schedulers.io)
            .execute { favorites ->
                favorites.sortedBy { it.venue.name }
                isFavorite = favorites.firstOrNull { it.venue.venueId == venueId } != null
                copy(favoriteVenues = favorites)
            }

        disposables += updateBlockedVenues.observe()
            .toObservable()
            .subscribeOn(schedulers.io)
            .execute { blockedVenues ->
                isBlocked = blockedVenues.firstOrNull { it.venue.venueId == venueId } != null
                copy(blockedVenues = blockedVenues)
            }

        loadingState.observable
            .execute {
                copy(isLoading = it)
            }
    }

    fun setParams(venueId: String) {
        this.venueId = venueId
        updateVenueDetail.setParams(UpdateVenueDetail.Param(venueId))
    }

    fun updateContentDetail() {
        loadingState.addLoader()
        scope.launchInteractor(updateVenueDetail, UpdateVenueDetail.ExecuteParams(Unit))
            .invokeOnCompletion { loadingState.removeLoader() }
    }

    fun addToFavorites(venueId: String) {
        scope.launchInteractor(addToFavoriteVenues, AddToFavoriteVenues.Param(venueId))
    }

    fun removeFromFavorites(venueId: String) {
        scope.launchInteractor(removeFromFavoriteVenues, RemoveFromFavoriteVenues.Param(venueId))
    }

    fun addToBlockedVenues(venueId: String) {
        scope.launchInteractor(addToBlockedVenues, AddToBlockedVenues.Param(venueId))
    }

    fun removeFromBlockedVenues(venueId: String) {
        scope.launchInteractor(removeFromBlockedVenues, RemoveFromBlockedVenues.Param(venueId))
    }
}
