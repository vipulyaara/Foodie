package com.foodie.data.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.foodie.data.data.db.daos.FavoriteVenueEntryDao
import com.foodie.data.data.db.daos.NearbyVenueEntryDao
import com.foodie.data.data.db.daos.VenueDao
import com.foodie.data.data.db.daos.VenueDetailDao
import com.foodie.data.entities.FavoriteVenueEntry
import com.foodie.data.entities.NearbyVenueEntry
import com.foodie.data.entities.Venue
import com.foodie.data.entities.VenueDetail

/**
 * Database description.
 */
@Database(
    entities = [Venue::class, NearbyVenueEntry::class, VenueDetail::class, FavoriteVenueEntry::class],
    version = 1
)
@TypeConverters(FoodieTypeConverters::class)
abstract class FoodieDb : RoomDatabase() {
    abstract fun venueDao(): VenueDao
    abstract fun venueDetailDao(): VenueDetailDao
    abstract fun nearbyVenueEntryDao(): NearbyVenueEntryDao
    abstract fun favoriteVenueEntryDao(): FavoriteVenueEntryDao
}
