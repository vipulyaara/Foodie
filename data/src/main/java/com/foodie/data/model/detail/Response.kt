package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("venue")
    @Expose
    var venue: VenueDetailFromResponse? = null
}
