package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Warning {

    @SerializedName("text")
    @Expose
    var text: String? = null
}
