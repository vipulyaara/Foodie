package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Popular {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("isOpen")
    @Expose
    var isOpen: Boolean? = null
    @SerializedName("isLocalHoliday")
    @Expose
    var isLocalHoliday: Boolean? = null
    @SerializedName("timeframes")
    @Expose
    var timeframes: List<Timeframe_>? = null
}
