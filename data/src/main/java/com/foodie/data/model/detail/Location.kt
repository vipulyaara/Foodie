package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Location {

    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("crossStreet")
    @Expose
    var crossStreet: String? = null
    @SerializedName("lat")
    @Expose
    var lat: Double? = null
    @SerializedName("lng")
    @Expose
    var lng: Double? = null
    @SerializedName("postalCode")
    @Expose
    var postalCode: String? = null
    @SerializedName("cc")
    @Expose
    var cc: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("formattedAddress")
    @Expose
    var formattedAddress: List<String>? = null
}
