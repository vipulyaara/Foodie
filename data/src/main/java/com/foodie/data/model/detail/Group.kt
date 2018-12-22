package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Group {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
}
