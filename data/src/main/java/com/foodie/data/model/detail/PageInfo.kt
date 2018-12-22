package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PageInfo {

    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("banner")
    @Expose
    var banner: String? = null
    @SerializedName("links")
    @Expose
    var links: Links? = null
}
