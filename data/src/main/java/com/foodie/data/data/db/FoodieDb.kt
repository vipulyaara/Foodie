package com.foodie.data.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.foodie.data.data.db.daos.NearbyVenueEntryDao
import com.foodie.data.data.db.daos.VenueDao
import com.foodie.data.entities.NearbyVenueEntry
import com.foodie.data.model.Venue

/**
 * Database description.
 */
@Database(
    entities = [Venue::class, NearbyVenueEntry::class],
    version = 1
)

abstract class FoodieDb : RoomDatabase() {
    abstract fun venueDao(): VenueDao
    abstract fun nearbyVenueEntryDao(): NearbyVenueEntryDao
}
