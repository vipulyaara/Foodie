package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo____ {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: Int? = null
    @SerializedName("prefix")
    @Expose
    var prefix: String? = null
    @SerializedName("suffix")
    @Expose
    var suffix: String? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("user")
    @Expose
    var user: User____? = null
    @SerializedName("visibility")
    @Expose
    var visibility: String? = null
}
