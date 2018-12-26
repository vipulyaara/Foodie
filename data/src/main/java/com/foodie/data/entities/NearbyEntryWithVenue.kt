package com.foodie.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import java.util.Objects

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Resulting model for nearby venue entities.
 *
 */
class NearbyEntryWithVenue : EntryWithVenue<NearbyVenueEntry> {
    @Embedded
    override var entry: NearbyVenueEntry? = null

    @Relation(parentColumn = "venue_id", entityColumn = "venue_id")
    var favorite: List<FavoriteVenueEntry>? = null

    @Relation(parentColumn = "venue_id", entityColumn = "venue_id")
    override var relations: List<Venue> = emptyList()

    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other is NearbyEntryWithVenue -> entry == other.entry && relations == other.relations
        else -> false
    }

    override fun hashCode(): Int = Objects.hash(entry, relations)
}
