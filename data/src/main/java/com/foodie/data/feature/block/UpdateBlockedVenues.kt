package com.foodie.data.feature.block

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.entities.BlockedEntryWithVenue
import com.foodie.data.feature.SubjectInteractor
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.generic.instance

/**
 * Interactor for updating the blocked venues.
 * */
class UpdateBlockedVenues :
    SubjectInteractor<UpdateBlockedVenues.Param, UpdateBlockedVenues.ExecuteParams, List<BlockedEntryWithVenue>>() {

    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val repository: BlockedVenueRepository by kodeinInstance.instance()

    override val dispatcher: CoroutineDispatcher = dispatchers.io

    init {
        setParams(Param())
    }

    override fun createObservable(params: Param): Flowable<List<BlockedEntryWithVenue>> {
        return repository.observeBlockedVenues()
            .subscribeOn(schedulers.io)
    }

    override suspend fun execute(params: Param, executeParams: ExecuteParams) {
        // do nothing
    }

    class Param

    data class ExecuteParams(val unit: Unit)
}
