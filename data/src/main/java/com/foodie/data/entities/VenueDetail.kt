package com.foodie.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.foodie.data.model.detail.Attribute
import com.foodie.data.model.detail.Category
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
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "categories") var categories: List<Category>? = null,
    @ColumnInfo(name = "tip") var tip: String? = null,
    @ColumnInfo(name = "attributes") var attributes: List<Attribute>? = null
) : BaseEntity {

    fun isEmpty() = venueId.isEmpty()
}

fun <R> VenueDetail?.letEmpty(block: (VenueDetail) -> R) {
    if (this?.venueId?.isNotEmpty() == true) {
        block(this)
    }
}

fun VenueDetailFromResponse.toVenueDetail() = VenueDetail(
    venueId = this.id ?: "",
    name = this.name,
    description = this.description,
    categories = this.categories,
    tip = this.tips?.groups?.map { it.items?.map { it.text } }?.get(0)?.get(0),
    photo = this.bestPhoto?.run { "$prefix${width}x$height$suffix" },
    attributes = this.toAttributes()
)

fun VenueDetailFromResponse.toAttributes(): List<Attribute> {
    val attributes = mutableListOf<Attribute>()
    val list = this.attributes?.groups?.forEach {
        it.items?.forEach {
            attributes.add(Attribute(it.displayName, it.displayValue))
        }
    }
    return attributes
}
