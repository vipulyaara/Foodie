package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Timeframe {

    @SerializedName("days")
    @Expose
    var days: String? = null
    @SerializedName("includesToday")
    @Expose
    var includesToday: Boolean? = null
    @SerializedName("open")
    @Expose
    var open: List<Open>? = null
    @SerializedName("segments")
    @Expose
    var segments: List<Any>? = null
}
