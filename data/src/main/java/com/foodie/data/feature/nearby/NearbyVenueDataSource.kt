package com.foodie.data.feature.nearby

import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.api.FoodieApi
import com.foodie.data.data.api.RetrofitRunner
import com.foodie.data.entities.NearbyVenueEntry
import com.foodie.data.entities.Venue
import com.foodie.data.extensions.executeWithRetry
import com.foodie.data.feature.common.DataSource
import com.foodie.data.mapper.NearbyApiMapper
import com.foodie.data.model.Result
import org.kodein.di.generic.instance

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Remote data source for nearby venues.
 */

const val api_radius = 6000
const val api_section_food = "food"
const val api_nearby_count = 10
const val openNow = 1

fun offset(page: Int) = page * api_nearby_count
class NearbyVenueDataSource : DataSource() {

    private val foodieApi: FoodieApi by kodeinInstance.instance()
    private val retrofitRunner: RetrofitRunner by kodeinInstance.instance()
    private val nearbyApiMapper: NearbyApiMapper by kodeinInstance.instance()

    suspend fun getVenues(
        latLong: String,
        page: Int
    ): Result<List<Pair<Venue, NearbyVenueEntry>>> {
        return retrofitRunner.executeForResponse(nearbyApiMapper) {
            foodieApi.fetchNearbyVenues(
                latLong = latLong,
                radius = api_radius,
                section = api_section_food,
                count = api_nearby_count,
                offset = offset(page),
                openNow = openNow
            )
                .executeWithRetry()
        }
    }
}
