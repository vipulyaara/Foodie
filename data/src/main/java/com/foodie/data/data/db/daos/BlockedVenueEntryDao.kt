package com.foodie.data.data.db.daos

import androidx.room.Dao
import com.foodie.data.entities.BlockedEntryWithVenue
import com.foodie.data.entities.BlockedVenueEntry

@Dao
abstract class BlockedVenueEntryDao : EntryDao<BlockedVenueEntry, BlockedEntryWithVenue>
