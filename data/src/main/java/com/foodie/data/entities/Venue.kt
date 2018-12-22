package com.foodie.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.foodie.data.model.nearby.VenueFromResponse

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 */
@Entity(
    tableName = "venues",
    indices = [Index(value = ["venue_id"], unique = true)]
)
data class Venue(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override var id: Long = 0,
    @ColumnInfo(name = "venue_id") var venueId: String = "",
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "location") var location: String? = null
) : BaseEntity

fun VenueFromResponse.toVenue() = Venue(
    venueId = this.id ?: "",
    name = this.name,
    location = this.location?.city
)

fun VenueFromResponse.toVenueEntry(index: Int) = NearbyVenueEntry(
    venueId = this.id ?: "",
    page = 0,
    pageOrder = index,
    ll = this.location?.run { "$lat,$lng" }
)
