package com.foodie.data.entities

import java.util.Objects

/**
 * Generic interface to use venues with entries.
 *
 * It can simplify the re-usability of venues in entities
 * like nearby venues, favorite venues, disliked venues etc.
 */
interface EntryWithVenue<ET : Entry> {
    var entry: ET?
    var relations: List<Venue>

    val venue: Venue
        get() {
            assert(relations.size == 1)
            return relations[0]
        }

    fun generateStableId(): Long {
        return Objects.hash(entry!!::class, venue.venueId).toLong()
    }
}
