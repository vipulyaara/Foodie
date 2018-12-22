package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VenuePage(
    @SerializedName("id")
    @Expose
    var id: String? = null
)
