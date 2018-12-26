package com.foodie.data.data.api

import com.foodie.data.model.detail.VideoDetailResponse
import com.foodie.data.model.nearby.NearbyVenuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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
        @Query("radius") radius: Int?,
        @Query("section") section: String?,
        @Query("limit") count: Int?,
        @Query("offset") offset: Int?
    ): Call<NearbyVenuesResponse>

    @GET("v2/venues/{venueId}")
    fun fetchVenuedetail(
        @Path("venueId") venueId: String?
    ): Call<VideoDetailResponse>
}
