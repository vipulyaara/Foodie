package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address")
    @Expose
    var address: String? = null,
    @SerializedName("crossStreet")
    @Expose
    var crossStreet: String? = null,
    @SerializedName("lat")
    @Expose
    var lat: Double? = null,
    @SerializedName("lng")
    @Expose
    var lng: Double? = null,
    @SerializedName("labeledLatLngs")
    @Expose
    var labeledLatLngs: List<LabeledLatLng>? = null,
    @SerializedName("distance")
    @Expose
    var distance: Int? = null,
    @SerializedName("postalCode")
    @Expose
    var postalCode: String? = null,
    @SerializedName("cc")
    @Expose
    var cc: String? = null,
    @SerializedName("city")
    @Expose
    var city: String? = null,
    @SerializedName("state")
    @Expose
    var state: String? = null,
    @SerializedName("country")
    @Expose
    var country: String? = null,
    @SerializedName("formattedAddress")
    @Expose
    var formattedAddress: List<String>? = null
)
