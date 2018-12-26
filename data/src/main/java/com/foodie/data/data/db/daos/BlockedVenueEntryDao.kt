package com.foodie.data.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.foodie.data.entities.BlockedEntryWithVenue
import com.foodie.data.entities.BlockedVenueEntry
import io.reactivex.Flowable

@Dao
abstract class BlockedVenueEntryDao :
    EntityDao<BlockedVenueEntry> {

    @Query("SELECT * from blocked_venue")
    abstract fun getBlockedVenuesFlowable(): Flowable<List<BlockedEntryWithVenue>>

    @Query("SELECT * from blocked_venue")
    abstract fun getBlockedVenues(): List<BlockedEntryWithVenue>

    @Query("DELETE from blocked_venue WHERE venue_id = :venueId")
    abstract fun delete(venueId: String)
}
