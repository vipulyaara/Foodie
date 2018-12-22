package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VenueFromResponse(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("location")
    @Expose
    var location: Location? = null,
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null,
    @SerializedName("venuePage")
    @Expose
    var venuePage: VenuePage? = null
)
