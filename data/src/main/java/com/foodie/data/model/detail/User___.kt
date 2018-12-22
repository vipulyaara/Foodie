package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User___ {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("lastName")
    @Expose
    var lastName: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("photo")
    @Expose
    var photo: Photo___? = null
}
