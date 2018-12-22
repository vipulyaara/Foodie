package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NearbyVenuesResponse(
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null,
    @SerializedName("response")
    @Expose
    var response: Response? = null
)
