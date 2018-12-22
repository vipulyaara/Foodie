package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Entity {

    @SerializedName("indices")
    @Expose
    var indices: List<Int>? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}
