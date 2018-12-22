package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code")
    @Expose
    var code: Int? = null,
    @SerializedName("requestId")
    @Expose
    var requestId: String? = null
)
