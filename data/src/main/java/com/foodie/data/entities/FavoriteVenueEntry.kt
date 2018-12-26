package com.foodie.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 * Entity to hold favorite venues.
 */

@Entity(
    tableName = "favorite_venue",
    indices = [
        Index(value = ["venue_id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Venue::class,
            parentColumns = arrayOf("venue_id"),
            childColumns = arrayOf("venue_id")
        )
    ]
)
data class FavoriteVenueEntry(
    @PrimaryKey(autoGenerate = true) override var id: Long = 0,
    @ColumnInfo(name = "venue_id") var venueId: String = ""
) : Entry
