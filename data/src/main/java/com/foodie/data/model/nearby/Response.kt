package com.foodie.data.model.nearby

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("warning")
    @Expose
    var warning: Warning? = null
    @SerializedName("suggestedRadius")
    @Expose
    var suggestedRadius: Int? = null
    @SerializedName("headerLocation")
    @Expose
    var headerLocation: String? = null
    @SerializedName("headerFullLocation")
    @Expose
    var headerFullLocation: String? = null
    @SerializedName("headerLocationGranularity")
    @Expose
    var headerLocationGranularity: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null
    @SerializedName("suggestedBounds")
    @Expose
    var suggestedBounds: SuggestedBounds? = null
    @SerializedName("groups")
    @Expose
    var groups: List<Group>? = null
}
