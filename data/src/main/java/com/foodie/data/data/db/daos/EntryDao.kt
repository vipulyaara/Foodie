package com.foodie.data.data.db.daos

import androidx.paging.DataSource
import com.foodie.data.entities.Entry
import com.foodie.data.entities.EntryWithVenue
import io.reactivex.Flowable

/**
 * This interface represents a DAO which contains entities which are part of a single collective list.
 */
interface EntryDao<EC : Entry, LI : EntryWithVenue<EC>> :
    EntityDao<EC> {
    fun entriesFlowable(count: Int, offset: Int): Flowable<List<LI>>
    fun entriesDataSource(): DataSource.Factory<Int, LI>
    fun deleteAll()
}
