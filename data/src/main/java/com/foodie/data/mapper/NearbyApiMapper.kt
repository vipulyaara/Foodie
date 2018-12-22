package com.foodie.data.mapper

import com.foodie.data.entities.NearbyVenueEntry
import com.foodie.data.model.Venue
import com.foodie.data.model.nearby.NearbyVenuesResponse
import com.foodie.data.model.toVenue
import com.foodie.data.model.toVenueEntry

/**
 * @author Vipul Kumar; dated 21/12/18.
 */
class NearbyApiMapper : Mapper<NearbyVenuesResponse, List<Pair<Venue, NearbyVenueEntry>>> {
    override fun map(from: NearbyVenuesResponse): List<Pair<Venue, NearbyVenueEntry>> {
        return from.response?.groups!![0].items?.mapIndexed { index, venueFromResponse ->
            Pair(
                venueFromResponse.venue!!.toVenue(),
                venueFromResponse.venue!!.toVenueEntry(index)
            )
        } ?: arrayListOf()
    }
}
