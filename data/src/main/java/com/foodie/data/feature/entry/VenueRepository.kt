package com.foodie.data.feature.entry

import com.foodie.data.model.Venue
import io.reactivex.Flowable

interface VenueRepository {
    suspend fun getVenue(entryId: String): Venue

    suspend fun updateVenue(entryId: String)

    fun needsUpdate(entryId: String): Boolean

    fun observeVenue(entryId: String): Flowable<Venue>
}
