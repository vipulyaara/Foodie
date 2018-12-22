package com.foodie.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("code")
    @Expose
    var code: Int? = null
    @SerializedName("requestId")
    @Expose
    var requestId: String? = null
}
