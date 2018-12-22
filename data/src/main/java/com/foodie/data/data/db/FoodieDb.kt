package com.foodie.data.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.foodie.data.data.db.daos.NearbyVenueEntryDao
import com.foodie.data.data.db.daos.VenueDao
import com.foodie.data.data.db.daos.VenueDetailDao
import com.foodie.data.entities.NearbyVenueEntry
import com.foodie.data.entities.Venue
import com.foodie.data.entities.VenueDetail

/**
 * Database description.
 */
@Database(
    entities = [Venue::class, NearbyVenueEntry::class, VenueDetail::class],
    version = 1
)

abstract class FoodieDb : RoomDatabase() {
    abstract fun venueDao(): VenueDao
    abstract fun venueDetailDao(): VenueDetailDao
    abstract fun nearbyVenueEntryDao(): NearbyVenueEntryDao
}
