package com.foodie.data.utils

import com.foodie.data.data.db.FoodieDb
import com.foodie.data.entities.NearbyVenueEntry
import com.foodie.data.entities.Venue

const val showId = 1L
const val venueId = "1"
val venue1 = Venue(id = showId, venueId = venueId, name = "Down Under")

const val show2Id = 2L
const val venue2Id = "2"
val venue2 = Venue(id = show2Id, venueId = venue2Id, name = "G'day mate")

fun insertShow(db: FoodieDb) = db.venueDao().insert(venue1)

fun deleteShow(db: FoodieDb) = db.venueDao().delete(venue1)

fun insertFollowedShow(db: FoodieDb) = db.nearbyVenueEntryDao().insert(nearbyVenueEntry1)

const val id1 = 1L
val nearbyVenueEntry1 = NearbyVenueEntry(id1, venueId, 0, 0)
// val followedShow1PendingDelete = nearbyVenueEntry1.copy(pendingAction = PendingAction.DELETE)
// val followedShow1PendingUpload = nearbyVenueEntry1.copy(pendingAction = PendingAction.UPLOAD)
//
// const val followedShow2Id = 2L
// val followedShow2 = FollowedShowEntry(followedShow2Id, show2Id)
