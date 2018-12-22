package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sample {

    @SerializedName("entities")
    @Expose
    var entities: List<Entity>? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
}
