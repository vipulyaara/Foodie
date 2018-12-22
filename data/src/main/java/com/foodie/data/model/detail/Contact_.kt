package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contact_ {

    @SerializedName("twitter")
    @Expose
    var twitter: String? = null
    @SerializedName("facebook")
    @Expose
    var facebook: String? = null
}
