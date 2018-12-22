package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contact {

    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("formattedPhone")
    @Expose
    var formattedPhone: String? = null
    @SerializedName("twitter")
    @Expose
    var twitter: String? = null
    @SerializedName("instagram")
    @Expose
    var instagram: String? = null
    @SerializedName("facebook")
    @Expose
    var facebook: String? = null
    @SerializedName("facebookUsername")
    @Expose
    var facebookUsername: String? = null
    @SerializedName("facebookName")
    @Expose
    var facebookName: String? = null
}
