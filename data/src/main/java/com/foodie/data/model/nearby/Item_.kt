package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item_ {

    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("reasonName")
    @Expose
    var reasonName: String? = null
}
