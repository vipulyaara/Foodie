package com.foodie.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.foodie.data.model.detail.VenueDetailFromResponse

/**
 * @author Vipul Kumar; dated 21/12/18.
 *
 */
@Entity(
    tableName = "venue_detail",
    indices = [Index(value = ["venue_id"], unique = true)]
)
data class VenueDetail(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override var id: Long = 0,
    @ColumnInfo(name = "venue_id") var venueId: String = "",
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "photo") var photo: String? = null,
    @ColumnInfo(name = "description") var description: String? = null
) : BaseEntity

fun VenueDetailFromResponse.toVenueDetail() = VenueDetail(
    venueId = this.id ?: "",
    name = this.name,
    description = this.description
)
