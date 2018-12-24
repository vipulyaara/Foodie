package com.foodie.data.feature.detail

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.entities.VenueDetail
import com.foodie.data.feature.SubjectInteractor
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.generic.instance

/**
 * Interactor for updating the venue detail.
 * */
class UpdateVenueDetail :
    SubjectInteractor<UpdateVenueDetail.Param, UpdateVenueDetail.ExecuteParams, VenueDetail>() {

    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val venueDetailRepository: VenueDetailRepository by kodeinInstance.instance()

    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override fun createObservable(params: Param): Flowable<VenueDetail> {
        return venueDetailRepository.observeContentDetail(params.venueId)
            .subscribeOn(schedulers.io)
    }

    override suspend fun execute(params: Param, executeParams: ExecuteParams) {
        venueDetailRepository.updateContentDetail(params.venueId)
    }

    data class Param(val venueId: String)

    data class ExecuteParams(val unit: Unit)
}
