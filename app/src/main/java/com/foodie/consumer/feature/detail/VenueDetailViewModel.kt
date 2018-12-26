package com.foodie.consumer.feature.detail

import com.foodie.consumer.config.RxLoadingCounter
import com.foodie.consumer.feature.common.BaseViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.data.Logger
import com.foodie.data.feature.detail.UpdateVenueDetail
import com.foodie.data.feature.favorite.AddToFavoriteVenues
import com.foodie.data.feature.favorite.UpdateFavoriteVenues
import com.foodie.data.feature.launchInteractor
import io.reactivex.rxkotlin.plusAssign
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/12/18.
 *
 * ViewModel for venue details.
 */
class VenueDetailViewModel : BaseViewModel<VenueDetailViewState>(VenueDetailViewState()) {
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val updateVenueDetail: UpdateVenueDetail by kodeinInstance.instance()
    private val updateFavoriteVenues: UpdateFavoriteVenues by kodeinInstance.instance()
    private val addToFavoriteVenues: AddToFavoriteVenues by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    private val loadingState = RxLoadingCounter()

    init {
        disposables += updateVenueDetail.observe()
            .toObservable()
            .subscribeOn(schedulers.io)
            .execute { copy(venueDetail = it) }

        disposables += updateFavoriteVenues.observe()
            .toObservable()
            .subscribeOn(schedulers.io)
            .execute { copy(favoriteVenues = it) }

        loadingState.observable
            .execute {
                logger.d("loader $it")
                copy(isLoading = it)
            }
    }

    fun setParams(venueId: String) {
        loadingState.addLoader()
        updateVenueDetail.setParams(UpdateVenueDetail.Param(venueId))
        scope.launchInteractor(updateVenueDetail, UpdateVenueDetail.ExecuteParams(Unit))
            .invokeOnCompletion { loadingState.removeLoader() }
    }

    internal fun addToFavorites(venueId: String) {
        scope.launchInteractor(addToFavoriteVenues, AddToFavoriteVenues.Param(venueId))
    }

    internal fun removeFromFavorites(venueId: String) {
//        scope.launchInteractor(removeFromFavoriteVenues, RemoveFromFavoriteVenues.Param(venueId))
    }
}
