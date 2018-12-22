package com.foodie.data.mapper

import com.foodie.data.entities.NearbyVenueEntry
import com.foodie.data.entities.Venue
import com.foodie.data.entities.toVenue
import com.foodie.data.entities.toVenueEntry
import com.foodie.data.model.nearby.NearbyVenuesResponse

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
