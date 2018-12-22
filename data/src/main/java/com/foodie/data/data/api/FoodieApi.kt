package com.foodie.data.data.api

import com.foodie.data.model.nearby.NearbyVenuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * configures the API module and provides services to interact with the APIs.
 */
interface FoodieApi {
    @GET("v2/venues/explore")
    fun fetchNearbyVenues(
        @Query("ll") latLong: String?,
        @Query("section") section: String?,
        @Query("offset") offset: Int?,
        @Query("openNow") openNow: Int?
    ): Call<NearbyVenuesResponse>
}
