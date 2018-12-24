package com.foodie.consumer.feature.nearby

import androidx.paging.DataSource
import com.foodie.consumer.feature.entry.EntryViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.feature.launchInteractor
import com.foodie.data.feature.nearby.UpdateNearbyVenues
import kotlinx.coroutines.coroutineScope
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Implementation of [EntryViewModel] to fetch nearby venues.
 **/
class NearbyVenueViewModel : EntryViewModel<NearbyEntryWithVenue>() {

    private val interactor: UpdateNearbyVenues by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    override lateinit var dataSource: DataSource.Factory<Int, NearbyEntryWithVenue>

    fun setParams(params: UpdateNearbyVenues.Params) {
        interactor.setParams(params)
        dataSource = interactor.dataSourceFactory(params)
    }

    override suspend fun callLoadMore() = coroutineScope {
        launchInteractor(
            interactor,
            UpdateNearbyVenues.ExecuteParams(UpdateNearbyVenues.Page.NEXT_PAGE)
        ).join()
    }

    override suspend fun callRefresh() = coroutineScope {
        launchInteractor(
            interactor,
            UpdateNearbyVenues.ExecuteParams(UpdateNearbyVenues.Page.REFRESH)
        ).join()
    }
}
