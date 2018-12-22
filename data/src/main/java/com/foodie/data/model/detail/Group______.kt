package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Group______ {

    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<Item_____>? = null
}
