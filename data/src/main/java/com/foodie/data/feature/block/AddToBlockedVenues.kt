package com.foodie.data.feature.block

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.AppCoroutineDispatchers
import com.foodie.data.feature.Interactor
import kotlinx.coroutines.CoroutineDispatcher
import org.kodein.di.generic.instance

class AddToBlockedVenues :
    Interactor<AddToBlockedVenues.Param> {
    private val dispatchers: AppCoroutineDispatchers by kodeinInstance.instance()
    private val repository: BlockedVenueRepository by kodeinInstance.instance()

    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override suspend fun invoke(executeParams: Param) {
        repository.addToBlockedVenues(executeParams.venueId)
    }

    data class Param(val venueId: String)
}
