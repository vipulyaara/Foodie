package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User__ {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo__? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}
