package com.foodie.data.feature.nearby

import androidx.paging.DataSource
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.data.Logger
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.extensions.emptyFlowableList
import com.foodie.data.feature.PagingInteractor
import com.foodie.data.feature.SubjectInteractor
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * implementation of a [PagingInteractor] to fetch and update nearby venues.
 */
class UpdateNearbyVenues constructor(
    dispatchers: AppCoroutineDispatchers,
    private val schedulers: AppRxSchedulers,
    private val nearbyVenueRepository: NearbyVenueRepository
) : PagingInteractor<NearbyEntryWithVenue, UpdateNearbyVenues.Params>,
    SubjectInteractor<UpdateNearbyVenues.Params, UpdateNearbyVenues.ExecuteParams, List<NearbyEntryWithVenue>>() {

    private val logger: Logger by kodeinInstance.instance()
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override fun dataSourceFactory(params: Params): DataSource.Factory<Int, NearbyEntryWithVenue> {
        return nearbyVenueRepository.observeForPaging()
    }

    override fun createObservable(params: Params): Flowable<List<NearbyEntryWithVenue>> {
        return nearbyVenueRepository.observeForFlowable()
            .startWith(emptyFlowableList())
            .subscribeOn(schedulers.io)
    }

    override suspend fun execute(params: Params, executeParams: ExecuteParams) {
        when (executeParams.page) {
            Page.NEXT_PAGE -> nearbyVenueRepository.loadNextPage(
                params.ll
            )
            Page.REFRESH -> nearbyVenueRepository.refresh(
                params.ll
            )
        }
    }

    data class Params(val ll: String)

    data class ExecuteParams(val page: Page)

    enum class Page {
        NEXT_PAGE, REFRESH
    }
}
