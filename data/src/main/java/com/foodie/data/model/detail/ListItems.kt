package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListItems {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<Item____>? = null
}
