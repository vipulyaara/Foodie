package com.foodie.data.feature.favorite

import androidx.paging.DataSource
import com.foodie.data.config.di.kodeinInstance
import com.foodie.data.data.Logger
import com.foodie.data.data.db.DatabaseTransactionRunner
import com.foodie.data.data.db.daos.FavoriteVenueEntryDao
import com.foodie.data.entities.FavoriteEntryWithVenue
import com.foodie.data.entities.FavoriteVenueEntry
import io.reactivex.Flowable
import org.kodein.di.generic.instance

class LocalFavoriteVenueStore {
    private val transactionRunner: DatabaseTransactionRunner by kodeinInstance.instance()
    private val favoriteVenueEntryDao: FavoriteVenueEntryDao by kodeinInstance.instance()
    private val logger: Logger by kodeinInstance.instance()

    fun observeForFlowable(
        count: Int,
        offset: Int
    ): Flowable<List<FavoriteEntryWithVenue>> {
        return favoriteVenueEntryDao.entriesFlowable(count, offset)
    }

    fun observeForPaging(): DataSource.Factory<Int, FavoriteEntryWithVenue> =
        favoriteVenueEntryDao.entriesDataSource()

    fun addToFavorites(favoriteVenueEntry: FavoriteVenueEntry) {
        favoriteVenueEntryDao.insert(favoriteVenueEntry)
    }

    fun removeFromFavorites(favoriteVenueEntry: FavoriteVenueEntry) {
        favoriteVenueEntryDao.insert(favoriteVenueEntry)
    }

    fun deleteAll() = favoriteVenueEntryDao.deleteAll()

    fun getLastPage(): Int? = favoriteVenueEntryDao.getLastPage()
}
