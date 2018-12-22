package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Reasons {

    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("items")
    @Expose
    var items: List<Item_>? = null
}
