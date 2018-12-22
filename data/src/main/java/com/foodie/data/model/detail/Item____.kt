package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item____ {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("createdAt")
    @Expose
    var createdAt: Int? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo______? = null
}
