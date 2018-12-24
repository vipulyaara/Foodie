package com.foodie.consumer.feature.favorites

import androidx.paging.DataSource
import com.foodie.consumer.feature.entry.EntryViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.feature.favorite.AddToFavoriteVenues
import com.foodie.data.feature.favorite.RemoveFromFavoriteVenues
import com.foodie.data.feature.favorite.UpdateFavoriteVenues
import com.foodie.data.feature.launchInteractor
import kotlinx.coroutines.coroutineScope
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/12/18.
 *
 * ViewModel for all actions related to favorite venues.
 */
class FavoriteVenueViewModel : EntryViewModel<FavoriteEntryWithVenue>() {

    private val addToFavoriteVenues: AddToFavoriteVenues by kodeinInstance.instance()
    private val removeFromFavoriteVenues: RemoveFromFavoriteVenues by kodeinInstance.instance()
    private val interactor: UpdateFavoriteVenues by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    override lateinit var dataSource: DataSource.Factory<Int, FavoriteEntryWithVenue>

    fun setParams(params: UpdateFavoriteVenues.Params) {
        interactor.setParams(params)
        dataSource = interactor.dataSourceFactory(params)
    }

    internal fun addToFavorites(venueId: String) {
        scope.launchInteractor(addToFavoriteVenues, AddToFavoriteVenues.Param(venueId))
    }

    internal fun removeFromFavorites(venueId: String) {
        scope.launchInteractor(removeFromFavoriteVenues, RemoveFromFavoriteVenues.Param(venueId))
    }

    override suspend fun callLoadMore() = coroutineScope {
        launchInteractor(
            interactor,
            UpdateFavoriteVenues.ExecuteParams(UpdateFavoriteVenues.Page.NEXT_PAGE)
        ).join()
    }

    override suspend fun callRefresh() = coroutineScope {
        logger.d("call fetchFavorites")
        launchInteractor(
            interactor,
            UpdateFavoriteVenues.ExecuteParams(UpdateFavoriteVenues.Page.REFRESH)
        ).join()
    }
}
