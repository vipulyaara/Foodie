package com.foodie.data.feature.favorite

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.feature.SubjectInteractor
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.generic.instance

/**
 * Interactor for updating the favorite venues.
 * */
class UpdateFavoriteVenues :
    SubjectInteractor<UpdateFavoriteVenues.Param, UpdateFavoriteVenues.ExecuteParams, List<FavoriteEntryWithVenue>>() {

    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val favoriteVenueRepository: FavoriteVenueRepository by kodeinInstance.instance()

    override val dispatcher: CoroutineDispatcher = dispatchers.io

    init {
        setParams(Param())
    }

    override fun createObservable(params: Param): Flowable<List<FavoriteEntryWithVenue>> {
        return favoriteVenueRepository.observeFavoriteVenues()
            .subscribeOn(schedulers.io)
    }

    override suspend fun execute(params: Param, executeParams: ExecuteParams) {
        // do nothing
    }

    class Param

    data class ExecuteParams(val unit: Unit)
}
