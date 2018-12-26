package com.foodie.consumer.feature.nearby

import com.foodie.consumer.feature.entry.EntryViewModel
import com.foodie.consumer.feature.entry.EntryViewState
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.feature.favorite.AddToFavoriteVenues
import com.foodie.data.feature.favorite.RemoveFromFavoriteVenues
import com.foodie.data.feature.favorite.UpdateFavoriteVenues
import com.foodie.data.feature.launchInteractor
import com.foodie.data.feature.nearby.UpdateNearbyVenues
import com.foodie.data.model.Status
import com.foodie.data.model.UiResource
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.coroutineScope
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Implementation of [EntryViewModel] to fetch nearby venues.
 **/
class NearbyVenueViewModel :
    EntryViewModel<NearbyEntryWithVenue, EntryViewState<NearbyEntryWithVenue>>(
        entryViewState = EntryViewState(UiResource(Status.SUCCESS), null)
    ) {
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val updateNearbyVenues: UpdateNearbyVenues by kodeinInstance.instance()
    private val updateFavoriteVenues: UpdateFavoriteVenues by kodeinInstance.instance()
    private val addToFavoriteVenues: AddToFavoriteVenues by kodeinInstance.instance()
    private val removeFromFavoriteVenues: RemoveFromFavoriteVenues by kodeinInstance.instance()

    init {
        dataSource = updateNearbyVenues.dataSourceFactory()

        disposables += updateFavoriteVenues.observe()
            .toObservable().subscribeOn(schedulers.io)
            .execute { state }
    }

    fun setParams(params: UpdateNearbyVenues.Params) {
        updateNearbyVenues.setParams(params)
    }

    override suspend fun callLoadMore() = coroutineScope {
        logger.d("ViewModel loadMore")
        launchInteractor(
            updateNearbyVenues,
            UpdateNearbyVenues.ExecuteParams(UpdateNearbyVenues.Page.NEXT_PAGE)
        ).join()
    }

    override suspend fun callRefresh() = coroutineScope {
        launchInteractor(
            updateNearbyVenues,
            UpdateNearbyVenues.ExecuteParams(UpdateNearbyVenues.Page.REFRESH)
        ).join()
    }

    internal fun addToFavorites(venueId: String) {
        scope.launchInteractor(addToFavoriteVenues, AddToFavoriteVenues.Param(venueId))
    }

    internal fun removeFromFavorites(venueId: String) {
        scope.launchInteractor(removeFromFavoriteVenues, RemoveFromFavoriteVenues.Param(venueId))
    }
}
