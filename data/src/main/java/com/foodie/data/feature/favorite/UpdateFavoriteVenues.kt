package com.foodie.data.feature.favorite

import androidx.paging.DataSource
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.data.Logger
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.extensions.emptyFlowableList
import com.foodie.data.feature.PagingInteractor
import com.foodie.data.feature.SubjectInteractor
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.generic.instance

/**
 * Interactor for updating the venue detail.
 * */
class UpdateFavoriteVenues constructor(
    dispatchers: AppCoroutineDispatchers,
    private val schedulers: AppRxSchedulers,
    private val repository: FavoriteVenueRepository
) : PagingInteractor<FavoriteEntryWithVenue, UpdateFavoriteVenues.Params>,
    SubjectInteractor<UpdateFavoriteVenues.Params, UpdateFavoriteVenues.ExecuteParams, List<FavoriteEntryWithVenue>>() {

    private val logger: Logger by kodeinInstance.instance()
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override fun dataSourceFactory(params: Params): DataSource.Factory<Int, FavoriteEntryWithVenue> {
        return repository.observeForPaging()
    }

    override fun createObservable(params: Params): Flowable<List<FavoriteEntryWithVenue>> {
        return repository.observeForFlowable()
            .startWith(emptyFlowableList())
            .subscribeOn(schedulers.io)
    }

    override suspend fun execute(params: Params, executeParams: ExecuteParams) {
        when (executeParams.page) {
            Page.NEXT_PAGE -> repository.loadNextPage()
            Page.REFRESH -> repository.refresh()
        }
    }

    class Params

    data class ExecuteParams(val page: Page)

    enum class Page {
        NEXT_PAGE, REFRESH
    }
}
