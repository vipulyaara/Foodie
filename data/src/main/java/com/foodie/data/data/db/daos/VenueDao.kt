package com.foodie.data.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.foodie.data.model.Venue
import io.reactivex.Flowable

@Dao
abstract class VenueDao : EntityDao<Venue> {
    @Query("SELECT * FROM venues WHERE venue_id = :venueId")
    abstract fun getVenueWithIdFlowable(venueId: String): Flowable<Venue>

    @Query("SELECT * FROM venues WHERE venue_id = :venueId")
    abstract fun getVenueWithId(venueId: String): Venue?
}
