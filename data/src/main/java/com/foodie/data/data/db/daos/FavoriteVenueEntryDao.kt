package com.foodie.data.data.db.daos

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.entities.FavoriteVenueEntry
import io.reactivex.Flowable

@Dao
abstract class FavoriteVenueEntryDao :
    PaginatedEntryDao<FavoriteVenueEntry, FavoriteEntryWithVenue> {
    @Transaction
    @Query("SELECT * FROM favorite_venues ORDER BY page, page_order LIMIT :count OFFSET :offset")
    abstract override fun entriesFlowable(
        count: Int,
        offset: Int
    ): Flowable<List<FavoriteEntryWithVenue>>

    @Transaction
    @Query("SELECT * FROM favorite_venues ORDER BY page, page_order")
    abstract override fun entriesDataSource(): DataSource.Factory<Int, FavoriteEntryWithVenue>

    @Query("DELETE FROM favorite_venues WHERE page = :page")
    abstract override fun deletePage(page: Int)

    @Query("DELETE FROM favorite_venues")
    abstract override fun deleteAll()

    @Query("SELECT MAX(page) from favorite_venues")
    abstract override fun getLastPage(): Int?
}
