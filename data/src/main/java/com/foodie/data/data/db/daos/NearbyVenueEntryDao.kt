package com.foodie.data.data.db.daos

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.foodie.data.entities.NearbyEntryWithVenue
import com.foodie.data.entities.NearbyVenueEntry
import io.reactivex.Flowable

@Dao
abstract class NearbyVenueEntryDao :
    PaginatedEntryDao<NearbyVenueEntry, NearbyEntryWithVenue> {
    @Transaction
//    LEFT JOIN blocked_venue blocked_venue ON nearby_venues.venue_id = blocked_venue.venue_id WHERE blocked_venue.venue_id IS NULL AND nearby_venues.venue_id is NOT NULL
    @Query("SELECT * FROM nearby_venues nearby_venues ORDER BY page, page_order LIMIT :count OFFSET :offset")
//    @Query("SELECT venues.*, venues.venue_id as favorite_venue_id, favorite_venues.id as favorite_id FROM nearby_venues LEFT JOIN favorite_venues ON favorite_venues.venue_id = nearby_venues.venue_id ORDER BY page, page_order LIMIT :count OFFSET :offset")
//    @Query("SELECT venues.*, venues.venue_id as favorite_venue_id, favorite_venue.id as favorite_id, nearby_venues.page as page, nearby_venues.page_order as page_order FROM venues LEFT JOIN favorite_venue ON venues.venue_id = favorite_venue.venue_id LEFT JOIN nearby_venues ON venues.venue_id = nearby_venues.venue_id ORDER BY page, page_order LIMIT :count OFFSET :offset")
    abstract override fun entriesFlowable(
        count: Int,
        offset: Int
    ): Flowable<List<NearbyEntryWithVenue>>

    @Transaction
    @Query("SELECT * FROM nearby_venues ORDER BY page, page_order")
    abstract override fun entriesDataSource(): DataSource.Factory<Int, NearbyEntryWithVenue>

    @Query("DELETE FROM nearby_venues WHERE page = :page")
    abstract override fun deletePage(page: Int)

    @Query("DELETE FROM nearby_venues")
    abstract override fun deleteAll()

    @Query("SELECT MAX(page) from nearby_venues")
    abstract override fun getLastPage(): Int?
}
