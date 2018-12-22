package com.foodie.data.data.db.daos

import com.foodie.data.entities.EntryWithVenue
import com.foodie.data.entities.PaginatedEntry

interface PaginatedEntryDao<EC : PaginatedEntry, LI : EntryWithVenue<EC>> :
    EntryDao<EC, LI> {
    fun deletePage(page: Int, targetId: String)
    fun getLastPage(): Int?
}
