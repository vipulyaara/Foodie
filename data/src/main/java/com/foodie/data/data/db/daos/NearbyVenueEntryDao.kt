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
    @Query("SELECT * FROM nearby_venues where ll = :ll ORDER BY page, page_order LIMIT :count OFFSET :offset")
    abstract override fun entriesFlowable(
        ll: String,
        count: Int,
        offset: Int
    ): Flowable<List<NearbyEntryWithVenue>>

    @Transaction
    @Query("SELECT * FROM nearby_venues where ll = :ll ORDER BY page, page_order")
    abstract override fun entriesDataSource(
        ll: String
    ): DataSource.Factory<Int, NearbyEntryWithVenue>

    @Query("DELETE FROM nearby_venues WHERE page = :page AND ll = :ll")
    abstract override fun deletePage(page: Int, ll: String)

    @Query("DELETE FROM nearby_venues WHERE ll = :ll")
    abstract override fun deleteAll(ll: String)

    @Query("SELECT MAX(page) from nearby_venues")
    abstract override fun getLastPage(): Int?
}
