package com.foodie.consumer.feature.detail

import com.foodie.consumer.RxLoadingCounter
import com.foodie.consumer.feature.common.BaseViewModel
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppRxSchedulers
import com.foodie.data.data.Logger
import com.foodie.data.feature.detail.UpdateVenueDetail
import com.foodie.data.feature.launchInteractor
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 22/12/18.
 */
class VenueDetailViewModel : BaseViewModel<VenueDetailViewState>() {

    private val schedulers: AppRxSchedulers by kodeinInstance.instance()
    private val updateVenueDetail: UpdateVenueDetail by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    private val loadingState = RxLoadingCounter()

    init {
        updateVenueDetail.observe()
            .toObservable()
            .subscribeOn(schedulers.io)
            .subscribe { copy(VenueDetailViewState(it, false)) }
            .dispose()

//        loadingState.observable
//            .subscribe {
//                logger.d("loader added")
//                copy(isLoading = it() ?: false)
//            }
    }

    fun setparams(venueId: String) {
        updateVenueDetail.setParams(UpdateVenueDetail.Param(venueId))
        scope.launchInteractor(updateVenueDetail, UpdateVenueDetail.ExecuteParams(Unit))
            .invokeOnCompletion { loadingState.removeLoader() }
    }
}
