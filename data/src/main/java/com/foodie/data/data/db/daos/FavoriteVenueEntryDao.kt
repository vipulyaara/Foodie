package com.foodie.data.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.entities.FavoriteVenueEntry
import io.reactivex.Flowable

@Dao
abstract class FavoriteVenueEntryDao :
    EntityDao<FavoriteVenueEntry> {

    @Query("SELECT * from favorite_venue")
    abstract fun getFavoriteVenuesFlowable(): Flowable<List<FavoriteEntryWithVenue>>

    @Query("SELECT * from favorite_venue")
    abstract fun getFavoriteVenues(): List<FavoriteEntryWithVenue>
}
