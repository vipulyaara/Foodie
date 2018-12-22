package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SuggestedBounds {

    @SerializedName("ne")
    @Expose
    var ne: Ne? = null
    @SerializedName("sw")
    @Expose
    var sw: Sw? = null
}
