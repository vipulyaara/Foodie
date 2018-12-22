package com.foodie.data.feature.detail

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.api.FoodieApi
import com.foodie.data.data.api.RetrofitRunner
import com.foodie.data.entities.VenueDetail
import com.foodie.data.extensions.executeWithRetry
import com.foodie.data.mapper.VenueDetailMapper
import com.foodie.data.model.Result
import org.kodein.di.generic.instance

class VenueDetailDataSource {
    private val foodieApi: FoodieApi by kodeinInstance.instance()
    private val retrofitRunner: RetrofitRunner by kodeinInstance.instance()
    private val venueDetailMapper: VenueDetailMapper by kodeinInstance.instance()

    suspend fun getVenueDetail(venueId: String): Result<VenueDetail> {
        return retrofitRunner.executeForResponse(venueDetailMapper) {
            foodieApi.fetchVenuedetail(venueId)
                .executeWithRetry()
        }
    }
}
