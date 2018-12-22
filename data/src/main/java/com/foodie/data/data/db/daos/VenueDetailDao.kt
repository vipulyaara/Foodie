package com.foodie.data.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.foodie.data.entities.VenueDetail
import io.reactivex.Flowable

@Dao
abstract class VenueDetailDao : EntityDao<VenueDetail> {
    @Query("SELECT * FROM venue_detail WHERE venue_id = :venueId")
    abstract fun getVenueDetailWithIdFlowable(venueId: String): Flowable<VenueDetail>

    @Query("SELECT * FROM venue_detail WHERE venue_id = :venueId")
    abstract fun getVenueDetailWithId(venueId: String): VenueDetail?
}
